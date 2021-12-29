package com.example.jwt.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;

import java.sql.SQLException;

public class H2Config {

    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() throws SQLException {
        Server server = defaultRun();
        return new HikariDataSource();
    }

    private Server defaultRun() throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", 9095 + ""
        ).start();
    }

}
