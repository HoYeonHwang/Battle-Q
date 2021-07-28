package com.battleq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BattleQApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleQApplication.class, args);
    }

}