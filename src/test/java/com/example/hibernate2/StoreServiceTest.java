package com.example.hibernate2;

import com.example.hibernate2.dtos.ProductDto;
import com.example.hibernate2.mappers.ProductMapper;
import com.example.hibernate2.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class StoreServiceTest {

    @Test
    public void addTest() {
        ProductDto productDto = new ProductDto(1, "Test", "My descr",
                BigDecimal.valueOf(25));

        Product product = ProductMapper.INSTANCE.toProduct(productDto);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(productDto.getId(), product.getId());
        Assertions.assertEquals(productDto.getName(), product.getName());
        Assertions.assertEquals(productDto.getDescription(), product.getDescription());
        Assertions.assertEquals(productDto.getPrice(), product.getPrice());
    }

}
