package com.oauth2.example.config;

import com.oauth2.example.converters.UserConverter;
import com.oauth2.example.reverseconverters.UserDtoToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class GlobalAppConfig implements WebMvcConfigurer {

    @Autowired
    private DataSource dataSource;

    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserDtoToEntityConverter());
        registry.addConverter(new UserConverter());
    }
}
