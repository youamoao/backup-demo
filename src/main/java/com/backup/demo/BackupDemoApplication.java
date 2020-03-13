package com.backup.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BackupDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackupDemoApplication.class, args);
    }

}
