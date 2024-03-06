package com.example.capd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //서버켜자마자 크롤링되는거 싫으면 주석처리하세요
public class CapDApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapDApplication.class, args);
    }

}