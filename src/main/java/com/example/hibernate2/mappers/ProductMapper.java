package com.example.hibernate2.mappers;

import com.example.hibernate2.dtos.ProductDto;
import com.example.hibernate2.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);


}
