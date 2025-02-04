package org.example.serviceclients.mapper;

import org.example.common.models.ClientDTO;
import org.example.serviceclients.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    // Mappe un ClientDTO vers une entité Client
    Client toClient(ClientDTO clientDTO);

    // Mappe une entité Client vers un ClientDTO
    ClientDTO toClientDTO(Client client);

    // Met à jour un Client existant avec les champs d'un ClientDTO
    void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
