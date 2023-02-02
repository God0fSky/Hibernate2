package com.example.hibernate2.dtos;

import com.example.hibernate2.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientAndOrdersDto {

    private Integer id;

    private String name;

    private String email;

    private int phone;

    private AddressDto addressDto;

    List<Order> orderList;

}
