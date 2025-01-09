package org.example.servicecontrats;

import org.example.serviceclients.mapper.ClientMapper;
import org.example.serviceclients.mapper.ClientMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratServiceConfig {
    @Bean
    public ClientMapper clientMapper() {
        return new ClientMapperImpl();
    }
}
