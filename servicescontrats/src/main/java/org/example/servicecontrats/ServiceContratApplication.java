package org.example.servicecontrats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceContratApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceContratApplication.class, args);
    }

}
