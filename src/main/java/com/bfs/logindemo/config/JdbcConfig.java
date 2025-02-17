package com.bfs.logindemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

//    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/onlinequiz";
//    private final String USER = "root";
//    private final String PASSWORD = "0930";
//
//    @Bean
//    public DataSource jdbcDataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(JDBC_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(USER);
//        dataSource.setPassword(PASSWORD);
//        return dataSource;
//    }
}
