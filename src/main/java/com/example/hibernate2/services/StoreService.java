package com.example.hibernate2.services;


import com.example.hibernate2.config.AppConfiguration;
import com.example.hibernate2.dtos.*;
import com.example.hibernate2.models.*;
import com.example.hibernate2.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public ProductDto addProduct(ProductDto productDto) {
        Product product = objectMapper.convertValue(productDto, Product.class);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public ClientDto addClient(ClientDto clientDto) {
        Client client = objectMapper.convertValue(clientDto, Client.class);
        clientRepository.save(client);
        clientDto.setId(client.getId());
        return clientDto;
    }

    public AddressDto addAddress(AddressDto addressDto, int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        Address address = objectMapper.convertValue(addressDto, Address.class);
        address.setClient(client.orElseThrow(
                () ->new EntityNotFoundException("Client with id = " + clientId + " wasn`t found")
        ));
        addressRepository.save(address);
        addressDto.setId(address.getId());
        return addressDto;
    }

    public ClientDto changeAddress(int clientId, AddressDto addressDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(

                () -> new EntityNotFoundException("Client with id = " + clientId + " wasn`t found")
        );
        client.getAddress().setCountry(addressDto.getCountry());
        client.getAddress().setCity(addressDto.getCity());
        client.getAddress().setStreet(addressDto.getStreet());
        client.getAddress().setHouse(addressDto.getHouse());
        return objectMapper.convertValue(client, ClientDto.class);
    }

    public ClientDto getClientById(int id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () ->new EntityNotFoundException("Client with id = " + id + " wasn`t found")
        );
        ClientDto clientDto = objectMapper.convertValue(client, ClientDto.class);
        clientDto.setAddressDto(objectMapper.convertValue(client.getAddress(), AddressDto.class));
        //не понимаю почему, но без прямого сета адресс в дтошку не передается,  time to google
        return clientDto;
    }

    public OrderDto addOrder(int clientId, List<Integer> products) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id ="  + clientId + " wasn`t found")
        );
        /*ClientDto clientDto = objectMapper.convertValue(client, ClientDto.class);
        clientDto.setId(client.getId());*/
        //не хочет работать с дто, погуглить (unrecognized field...)
        OrderDto orderDto = new OrderDto(null, client, null);
        Order order = objectMapper.convertValue(orderDto, Order.class);
        orderRepository.save(order);
        //orderDto.setId(order.getId());

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (Integer product : products) {
            Product product1 = productRepository.findById(product).orElseThrow(
                    () -> new EntityNotFoundException("Product with id ="  + product + " wasn`t found")
            );
            orderItemDtos.add(addOrderItem(new OrderItemDto(null, 1,
                    product1,  order)));
        }

        List<OrderItem> orderItems = orderItemDtos.stream()
                .map(o -> objectMapper.convertValue(o, OrderItem.class))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return null;
    }

    public OrderItemDto addOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = objectMapper.convertValue(orderItemDto, OrderItem.class);
        orderItemRepository.save(orderItem);
        orderItemDto.setId(orderItem.getId());
        return objectMapper.convertValue(orderItem, OrderItemDto.class);
    }

    public ClientAndOrdersDto getClientAndOrdersById(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id ="  + clientId + " wasn`t found")
        );
        List<Order> orders = orderRepository.findAllByClient_Id(clientId);
        /*List<OrderDto> orderDtos = orders.stream()
                .map(order -> objectMapper.convertValue(orders, OrderDto.class))
                .collect(Collectors.toList());*/
        //опять проблема с дтошкой(unrecognized field), еще раз погуглить
        ClientAndOrdersDto clientAndOrdersDto = new ClientAndOrdersDto(client.getId(), client.getName(),
                client.getEmail(), client.getPhone(),
                objectMapper.convertValue(client.getAddress(), AddressDto.class), orders);
        return clientAndOrdersDto;
    }

    public ClientAndIdOrdersDto getClientAndIdOrdersById(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id ="  + clientId + " wasn`t found")
        );
        List<Order> orders = orderRepository.findAllByClient_Id(clientId);
        List<Integer> ids = new ArrayList<>();
        for (Order order : orders) {
            ids.add(order.getId());
        }
        /*List<OrderDto> orderDtos = orders.stream()
                .map(order -> objectMapper.convertValue(orders, OrderDto.class))
                .collect(Collectors.toList());*/
        //опять проблема с дтошкой(unrecognized field), еще раз погуглить
        ClientAndIdOrdersDto clientAndIdOrdersDto = new ClientAndIdOrdersDto(client.getId(), client.getName(),
                client.getEmail(), client.getPhone(), ids);
        return clientAndIdOrdersDto;
    }

}
