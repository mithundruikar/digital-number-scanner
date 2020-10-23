package com.scb.blade.number.scanner.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private MainScanner mainScanner;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainScanner.start(args[0]);
    }
}
