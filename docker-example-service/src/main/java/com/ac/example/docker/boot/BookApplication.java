package com.ac.example.docker.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@code BookApplication} represents the entry point for book controller
 * spring boot application.
 * <p/>
 *
 * @author Avinash
 * @since 2/23/17
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ac.example.docker.config",
        "com.ac.example.docker.controller",
        "com.ac.example.docker.error",
        "com.ac.example.docker.model",
        "com.ac.example.docker.service",
        "com.ac.example.docker.util"})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
