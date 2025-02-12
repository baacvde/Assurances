package org.example.servicenotifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ServiceNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceNotificationApplication.class, args);
    }

}
