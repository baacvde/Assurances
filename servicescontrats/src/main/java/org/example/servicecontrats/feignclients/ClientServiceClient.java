package org.example.servicecontrats.feignclients;


import org.example.common.models.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "servicesclients")
public interface ClientServiceClient {

    @GetMapping("/clients/{id}")
    Optional<ClientDTO> getClientById(@PathVariable("id") Long id);
}

