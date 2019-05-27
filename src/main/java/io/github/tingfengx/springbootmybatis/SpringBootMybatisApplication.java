package io.github.tingfengx.springbootmybatis;

import io.github.tingfengx.springbootmybatis.model.Users;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class, invokes Spring Boot
 *
 * @author eloisa
 * @createTime May 2019
 */
@MappedTypes(Users.class)
@MapperScan("io.github.tingfengx.springbootmybatis.dao")
@SpringBootApplication
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        System.out.println("==================" +
                "  default@http://localhost:30001/doc.html " +
                " =================");
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }
}
