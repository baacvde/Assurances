package org.example.serviceclients;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = {"org.example.common.entity","org.example.serviceclients"})
public class ServicesClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesClientsApplication.class, args);
    }

}