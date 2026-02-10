package com.spring.boot.jpa_docs.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com")
@EnableJpaRepositories(basePackages = "com.spring.boot.jpa_docs.dao")
public class AppConfig {
}
