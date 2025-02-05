package org.example.serviceclients.config;

import feign.RequestInterceptor;
import org.example.common.models.LoginDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


@Configuration
public class FeignClientConfig {

    private final LoginDto loginDto;

    public FeignClientConfig() {
        // Exemple : initialisation statique ou via configuration
        this.loginDto = new LoginDto("contratUser", "contratPassword");
    }

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            String authHeader = "Basic " + Base64.getEncoder()
                    .encodeToString((loginDto.getUsername() + ":" + loginDto.getPassword()).getBytes());
            requestTemplate.header("Authorization", authHeader);
        };
    }
}
