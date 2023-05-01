package org.klim.istock.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@Profile("heroku")
public class DatabaseConfig {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        Pattern urlPattern = Pattern.compile("postgres://([^:]+):([^@]+)@([^:]+):(\\d+)/(.+)");
        Matcher matcher = urlPattern.matcher(System.getenv("DATABASE_URL"));
        matcher.find();
        String dbUrl = String.format("jdbc:postgresql://%s:%s/%s", matcher.group(3), matcher.group(4), matcher.group(5));
        return DataSourceBuilder.create().url(dbUrl).username(matcher.group(1)).password(matcher.group(2)).build();
    }
}
