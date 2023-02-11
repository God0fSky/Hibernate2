package com.example.hibernate2.mappers;

import com.example.hibernate2.dtos.AddressDto;
import com.example.hibernate2.dtos.ClientDto;
import com.example.hibernate2.models.Address;
import com.example.hibernate2.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

}
