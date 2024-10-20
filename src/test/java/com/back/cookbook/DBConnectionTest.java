package com.back.cookbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DBConnectionTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() {
        jdbcTemplate.execute("SELECT 1");
    }
}
