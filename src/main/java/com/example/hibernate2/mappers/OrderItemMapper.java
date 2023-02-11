package com.example.hibernate2.mappers;

import com.example.hibernate2.dtos.OrderDto;
import com.example.hibernate2.dtos.OrderItemDto;
import com.example.hibernate2.models.Order;
import com.example.hibernate2.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
    @Mappings({
            @Mapping(source = "orderDto", target = "order"),
            @Mapping(source = "productDto", target = "product")
    })
    OrderItem toOrderItem(OrderItemDto orderItemDto);
    OrderItemDto toOrderItemDto(OrderItem orderItem);

}
