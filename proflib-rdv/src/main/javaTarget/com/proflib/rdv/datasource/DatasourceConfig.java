package com.proflib.rdv.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // n'est pas utilisé lors des Tests Unitaires
public class DatasourceConfig {

    @Bean
    public static DataSource dataSource() {
        //return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
        //        .url("jdbc:mysql://localhost:3306/myDb")
        //        .username("root")
        //        .password("pass").build();
        return null;
    }
}