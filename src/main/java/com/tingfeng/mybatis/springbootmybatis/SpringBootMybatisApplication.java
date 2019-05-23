package com.tingfeng.mybatis.springbootmybatis;

import com.tingfeng.mybatis.springbootmybatis.model.Users;
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
@MapperScan("com.tingfeng.mybatis.springbootmybatis.dao")
@SpringBootApplication
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        System.out.println("==================" +
                "  default@http://localhost:30001/doc.html " +
                " =================");
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }
}
