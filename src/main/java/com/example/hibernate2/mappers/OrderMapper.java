package com.example.hibernate2.mappers;

import com.example.hibernate2.dtos.ClientDto;
import com.example.hibernate2.dtos.OrderDto;
import com.example.hibernate2.models.Client;
import com.example.hibernate2.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "clientDto", target = "client"),
            @Mapping(source = "orderItemDtos", target = "orderItems"),
    })
    Order toOrder(OrderDto orderDto);

    @Mappings({
            @Mapping(source = "client", target = "clientDto"),
            @Mapping(source = "orderItems", target = "orderItemDtos"),
    })
    OrderDto toOrderDto(Order order);

}
