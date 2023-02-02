package com.example.hibernate2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientAndIdOrdersDto {

    private Integer id;

    private String name;

    private String email;

    private int phone;

    List<Integer> list;

}
