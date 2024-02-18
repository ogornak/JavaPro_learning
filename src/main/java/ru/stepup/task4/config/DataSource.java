package ru.stepup.task4.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource {
    @Value("${hikari.url}")
    private String url;
    @Value("${hikari.username}")
    private String username;
    @Value("${hikari.password}")
    private String password;
    @Value("${hikari.pool-size}")
    private int poolSize;
    private HikariDataSource ds;

    @PostConstruct
    public void init() {
        var config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(poolSize);
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @PreDestroy
    private void close() {
        ds.close();
    }
}
