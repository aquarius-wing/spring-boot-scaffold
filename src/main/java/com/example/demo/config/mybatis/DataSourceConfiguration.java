package com.example.demo.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.config.mybatis.properties.JdbcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DruidDataSource dataSource(@Autowired JdbcProperties jdbcProperties) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriver());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());

        dataSource.setInitialSize(jdbcProperties.getInitialSize());
        dataSource.setMinIdle(jdbcProperties.getMinIdle());
        dataSource.setMaxActive(jdbcProperties.getMaxActive());
        dataSource.setMaxWait(jdbcProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(jdbcProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(jdbcProperties.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(jdbcProperties.getValidationQuery());
        dataSource.setTestWhileIdle(jdbcProperties.getTestWhileIdle());
        dataSource.setTestOnBorrow(jdbcProperties.getTestOnBorrow());
        dataSource.setTestOnReturn(jdbcProperties.getTestOnReturn());
        dataSource.setPoolPreparedStatements(jdbcProperties.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcProperties.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setFilters(jdbcProperties.getFilters());
        return dataSource;
    }
}