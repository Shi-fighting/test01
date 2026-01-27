package com.itheima.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestJdbcTemplate {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdTemplate =
                (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        jdTemplate.execute("create table account(" +
                        "id int primary key auto_increment," +
                "username varchar(50)," + 	"balance double) charset=utf8mb4");
        System.out.println("账户表account创建成功！");}
}
