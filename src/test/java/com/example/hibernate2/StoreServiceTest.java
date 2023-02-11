package com.example.hibernate2;

import com.example.hibernate2.dtos.AddressDto;
import com.example.hibernate2.dtos.ClientDto;
import com.example.hibernate2.dtos.ProductDto;
import com.example.hibernate2.mappers.AddressMapper;
import com.example.hibernate2.mappers.ClientMapper;
import com.example.hibernate2.mappers.ProductMapper;
import com.example.hibernate2.models.Address;
import com.example.hibernate2.models.Client;
import com.example.hibernate2.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class StoreServiceTest {

    @Test
    void toProductTest() {
        ProductDto productDto = new ProductDto(1, "Test", "My descr",
                BigDecimal.valueOf(25));

        Product product = ProductMapper.INSTANCE.toProduct(productDto);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(productDto.getId(), product.getId());
        Assertions.assertEquals(productDto.getName(), product.getName());
        Assertions.assertEquals(productDto.getDescription(), product.getDescription());
        Assertions.assertEquals(productDto.getPrice(), product.getPrice());
    }

    @Test
    void toClientTest() {
        ClientDto clientDto = new ClientDto(1, "Test", "test@gmail.com", 123,
                new AddressDto(1, "Ukraine", "Ternopil",
                        "Ternopil`ska", "House 7"));

        Client client = ClientMapper.INSTANCE.toClient(clientDto);

        Assertions.assertNotNull(client);
        Assertions.assertEquals(clientDto.getId(), client.getId());
        Assertions.assertEquals(clientDto.getName(), client.getName());
        Assertions.assertEquals(clientDto.getEmail(), client.getEmail());
        Assertions.assertEquals(clientDto.getPhone(), client.getPhone());
    }

    @Test
    void toAddressTest() {
        AddressDto addressDto = new AddressDto(1, "Ukraine", "Ternopil",
                "Ternopil`ska", "House 7");

        Address address = AddressMapper.INSTANCE.toAddress(addressDto);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(addressDto.getId(), address.getId());
        Assertions.assertEquals(addressDto.getCountry(), address.getCountry());
        Assertions.assertEquals(addressDto.getCity(), address.getCity());
        Assertions.assertEquals(addressDto.getStreet(), address.getStreet());
        Assertions.assertEquals(addressDto.getHouse(), address.getHouse());

    }

}
