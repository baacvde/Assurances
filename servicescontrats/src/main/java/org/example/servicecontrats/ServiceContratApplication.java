package org.example.servicecontrats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = {
        "org.example.serviceclients",
        "org.example.servicecontrats",
        "org.example.serviceclients.mapper",
        "org.example.common.entity"
})
public class ServiceContratApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceContratApplication.class, args);
    }

}
