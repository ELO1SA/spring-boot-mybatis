package io.tingfeng.mybatis.springbootmybatis;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
     */
    private void deleteXiaoGang() throws SQLException {
        // Data base connection info
        try {
            String url = "jdbc:mysql://localhost:3306/FOO";
            String user = "eloisa";
            String password = "Stevenami0921";
            // 连接服务器
            con = DriverManager.getConnection(url, user, password);
            // 创建连接
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE salary=6666");
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

        // FIXME: This should be the same
        this.mockMvc.perform(get(restPoint + "/home"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "This is home page, although (almost) empty")));
    }

    /**
     * Test if the "/rest/users/all" tab that queries from mySQL server loads correctly
     *
     * @throws Exception w
     */
    @Test
    public void queryAllTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                restPoint + "/all", String.class))
                .contains("[{\"name\":\"xiaoming\",\"salary\":2333,\"id\":1}," +
                        "{\"name\":\"xiaohong\",\"salary\":4396,\"id\":2}");
    }

    /**
     * Test if the "/rest/users/update" tab successfully adds xiaogang to the mySQL
     * server. We first made sure that the table contains no xiaogang instance and
     * then queried the database.
     *
     * @throws Exception if the server wasn't able to respond as expected
     */
    @Test
    public void queryUpdateTest() throws Exception {
        deleteXiaoGang();
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                restPoint + "/all", String.class))
                .doesNotContain("{\"name\":\"xiaogang\",\"salary\":6666,\"id\":");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                restPoint + "/update", String.class))
                .contains("{\"name\":\"xiaogang\",\"salary\":6666,\"id\":");
        deleteXiaoGang();
    }
}
