package com.dx340.fileinterconnectionweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zsk-d
 */
@SpringBootApplication
@MapperScan("com.dx340.fileinterconnectionweb.mapper")
public class FileInterconnectionWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileInterconnectionWebApplication.class, args);
    }

}
