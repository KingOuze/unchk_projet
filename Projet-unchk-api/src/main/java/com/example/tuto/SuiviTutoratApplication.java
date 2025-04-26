package com.example.tuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@ComponentScan("com.example.tuto")
public class SuiviTutoratApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuiviTutoratApplication.class, args);
    }
}
