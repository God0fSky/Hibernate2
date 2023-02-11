package com.example.hibernate2.mappers;

import com.example.hibernate2.dtos.ClientDto;
import com.example.hibernate2.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toClient(ClientDto clientDto);
    ClientDto toClientDto(Client client);

}
