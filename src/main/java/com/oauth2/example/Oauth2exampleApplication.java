package com.oauth2.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class Oauth2exampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2exampleApplication.class, args);
    }
}
