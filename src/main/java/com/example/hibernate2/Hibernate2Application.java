package com.example.hibernate2;

import com.example.hibernate2.dtos.*;
import com.example.hibernate2.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Hibernate2Application {

    @Autowired
    private StoreService storeService;


    public static void main(String[] args) {
        SpringApplication.run(Hibernate2Application.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        /*storeService.addProduct(new ProductDto(null, "Banana",
                "Helps your hearth", BigDecimal.valueOf(25)));
        storeService.addClient(new ClientDto(null, "Client2",
                "lolol@gmail.com", +38098345, null));
        storeService.addAddress(new AddressDto(null, "Ukraine", "Ternopil",
                "Ternopil`ska", "House 7"), 4);*/
        /*storeService.changeAddress(4, new AddressDto(null, "Ukraine", "Kharkiv",
                "Kharkiv`ska", "House 8"));*/
        /*List<Integer> products = new ArrayList<>();
        products.add(3);
        products.add(5);
        storeService.addOrder(4,  products);*/
        ClientAndOrdersDto clientAndOrdersDto = storeService.getClientAndOrdersById(4);
        ClientDto clientDto = storeService.getClientById(4);
        ClientAndIdOrdersDto clientAndIdOrdersDto = storeService.getClientAndIdOrdersById(4);
    }
}
