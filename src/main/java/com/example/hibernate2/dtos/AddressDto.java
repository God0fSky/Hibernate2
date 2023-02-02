package com.example.hibernate2.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {

    private Integer id;

    private String country;

    private String city;

    private String street;

    private String house;


}
