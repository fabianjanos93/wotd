package com.codecool.mrguinneapig.wotd.wotd;

import com.codecool.mrguinneapig.wotd.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WotdApplication {


    public static void main(String[] args) {
        SpringApplication.run(WotdApplication.class, args);
    }
}
