package com.example.hibernate2.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {

    private Integer id;

    private String name;

    private String email;

    private int phone;

    private AddressDto addressDto;

}
