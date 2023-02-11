package com.example.hibernate2.dtos;



import com.example.hibernate2.models.Order;
import com.example.hibernate2.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    private Integer id;

    private int count;

    private ProductDto productDto;

    private OrderDto orderDto;


}
