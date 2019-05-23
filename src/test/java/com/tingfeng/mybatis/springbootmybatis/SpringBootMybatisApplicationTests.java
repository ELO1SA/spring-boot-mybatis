package com.tingfeng.mybatis.springbootmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * JUnit tests for basic functionality
 *
 * @author eloisa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringBootMybatisApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private final String restPoint = "/rest/users";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /* Fields for connecting to the database */
    private Connection con;
    private Statement stmt;

    /**
     * Make sure that the database contains what we want before we start the test,
     * using a test environment
     * <p>
     * This method is currently unusable, since the change to the post mapping of
     * "update"
     */
    private void initializeDatabase() throws SQLException {
        // Data base connection info
        try {
            String url = "jdbc:mysql://localhost:3306/FOO";
            String user = "usr";
            String password = "pswd";
            // 连接服务器
            con = DriverManager.getConnection(url, user, password);
            // 创建连接
            stmt = con.createStatement();
            // 小明和小红的id分别是1和2，此操作不会影响他们
            stmt.executeUpdate("DELETE FROM users WHERE id > 2");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
            stmt.close();
        }
    }

    /**
     * Test that if the context of the web application loadsS
     *
     * @throws Exception when the context of the web app cannot successfully load
     */
    @Test
    public void contextLoads() throws Exception {
    }

    /**
     * Test that if the home page was successfully loaded.
     *
     * @throws Exception when the home page was unable to load
     */
    @Test
    public void homePageTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                restPoint + "/home", String.class))
                .contains("This is home page, although (almost) empty");

        // This should be the same
        this.mockMvc.perform(get(restPoint + "/home"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "This is home page, although (almost) empty")));
    }

    /**
     * Test if the "/rest/users/all" tab that queries from mySQL server loads correctly
     */
    @Test
    public void queryAllTest() throws Exception {
        initializeDatabase();
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                restPoint + "/all", String.class))
                .contains("[{\"name\":\"xiaoming\",\"salary\":2333,\"id\":1}," +
                        "{\"name\":\"xiaohong\",\"salary\":4396,\"id\":2}");
    }

    /**
     * Test if the inserting one user at a time works
     */
    @Test
    public void insertOneUserTest() throws Exception {
        // clean up the database
        initializeDatabase();
        this.mockMvc.perform(post("http://localhost:" + port + restPoint +
                "/insert/name=Jane&salary=1000"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"name\":\"Jane\",\"salary\":1000,\"id\"")));
    }

    /**
     * Test if the database can handle multiple insertions
     */
    @Test
    public void insertMultipleUserTest() throws Exception {
        // clean up the database
        initializeDatabase();
        this.mockMvc.perform(post("http://localhost:" + port + restPoint +
                "/insert/name=Jane&salary=8848"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"name\":\"Jane\",\"salary\":8848,\"id\"")));
        this.mockMvc.perform(post("http://localhost:" + port + restPoint +
                "/insert/name=Steven&salary=1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"name\":\"Steven\",\"salary\":1,\"id\"")));
        this.mockMvc.perform(post("http://localhost:" + port + restPoint +
                "/insert/name=Shidifen&salary=3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"name\":\"Shidifen\",\"salary\":3,\"id\"")));
    }

    @Test
    public void deleteUserTest() throws Exception {
        // clean up the database
        initializeDatabase();
        this.mockMvc.perform(post("http://localhost:" + port + restPoint +
                "/insert/name=Jane&salary=8848"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"name\":\"Jane\",\"salary\":8848,\"id\"")));
        // now the database shall contain the user "Jane" w/ salary 8848
        this.mockMvc.perform(delete("http://localhost" + port + restPoint +
                "/delete/name=Jane"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString(
                        "{\"name\":\"Jane\",\"salary\":8848,\"id\""))));
    }
}
