package com.example.demo_secu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoSecuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSecuApplication.class, args);
    }

}
