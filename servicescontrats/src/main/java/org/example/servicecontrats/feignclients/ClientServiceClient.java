package org.example.servicecontrats.feignclients;


import feign.Param;
import org.example.common.models.ClientDTO;
import org.example.common.models.ContratDTO;
import org.example.servicecontrats.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ClientServiceClient", url = "http://localhost:8081/",configuration = FeignClientConfig.class)
public interface ClientServiceClient {

    @GetMapping("/api/clients/{id}")
    Optional<ClientDTO> getClientById(@PathVariable("id") Long id);



}

