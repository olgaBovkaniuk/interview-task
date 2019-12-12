package com.gmail.olgabovkaniuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.gmail.olgabovkaniuk")
@SpringBootApplication
public class LogManagerWebApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LogManagerWebApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LogManagerWebApp.class, args);
    }
}
