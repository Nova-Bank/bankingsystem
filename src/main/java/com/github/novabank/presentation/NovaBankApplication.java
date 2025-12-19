package com.github.novabank.presentation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.github.novabank")
public class NovaBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovaBankApplication.class, args);
    }
}