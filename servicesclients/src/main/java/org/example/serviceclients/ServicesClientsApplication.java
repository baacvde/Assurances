package org.example.serviceclients;

import org.springframework.cloud.openfeign.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class ServicesClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesClientsApplication.class, args);
    }

}