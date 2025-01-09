

package org.example.servicecontrats.mappers;

import org.example.common.models.ContratDTO;
import org.example.servicecontrats.Contrat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    @Mapping(source = "client.id", target = "clientId")
    ContratDTO toContratDTO(Contrat contrat);

    @Mapping(source = "clientId", target = "client.id") // Le client sera résolu séparément
    Contrat toContrat(ContratDTO contratDTO);
}

