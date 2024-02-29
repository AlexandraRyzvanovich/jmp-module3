package com.epam.module3.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfig {
  private static Logger LOGGER = LoggerFactory.getLogger(JdbcConfig.class);

//    @Bean
//    @ConditionalOnProperty(name = "custom.datasource.enabled", havingValue = "true", matchIfMissing = true)
//    public DataSource dataSource(DataSourceProperties properties) {
//        return DataSourceBuilder.create()
//                .driverClassName(properties.getDriverClassName())
//                .url(properties.getUrl())
//                .username(properties.getUsername())
//                .password(properties.getPassword())
//                .build();
//    }
}
