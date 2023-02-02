package com.example.hibernate2.dtos;


import com.example.hibernate2.models.Client;
import com.example.hibernate2.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private Integer id;

    private Client client;

    private List<OrderItem> orderItems      ;

}
