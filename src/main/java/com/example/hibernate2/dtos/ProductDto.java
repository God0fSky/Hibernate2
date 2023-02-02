package com.example.hibernate2.dtos;



import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

}
