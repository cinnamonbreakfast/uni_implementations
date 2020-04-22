package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:template.properties")
public class JdbcConfig {

    @Value("${db.url}") private String dbURL;
    @Value("${db.username}") private String dbUsername;
    @Value("${db.password}") private String dbPassword;

    @Bean
    JdbcOperations jdbcOperations() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

    @Bean
    DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl(dbURL);
        basicDataSource.setUsername(dbUsername);
        basicDataSource.setPassword(dbPassword);
        basicDataSource.setInitialSize(2);

        return basicDataSource;
    }
}
