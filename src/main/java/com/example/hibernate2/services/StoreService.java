package com.example.hibernate2.services;


import com.example.hibernate2.config.AppConfiguration;
import com.example.hibernate2.dtos.*;
import com.example.hibernate2.mappers.*;
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
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public ClientDto addClient(ClientDto clientDto) {
        Client client = ClientMapper.INSTANCE.toClient(clientDto);
        clientRepository.save(client);
        clientDto.setId(client.getId());
        return clientDto;
    }

    public AddressDto addAddress(AddressDto addressDto, int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        Address address = AddressMapper.INSTANCE.toAddress(addressDto);
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
        return ClientMapper.INSTANCE.toClientDto(client);
    }

    public ClientDto getClientById(int id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () ->new EntityNotFoundException("Client with id = " + id + " wasn`t found")
        );
        ClientDto clientDto = ClientMapper.INSTANCE.toClientDto(client);
        clientDto.setAddressDto(AddressMapper.INSTANCE.toAddressDto(client.getAddress()));
        return clientDto;
    }

    public OrderDto addOrder(int clientId, List<Integer> products) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id ="  + clientId + " wasn`t found")
        );
        ClientDto clientDto = ClientMapper.INSTANCE.toClientDto(client);
        clientDto.setId(client.getId());
        OrderDto orderDto = new OrderDto(null, clientDto, null);
        Order order = OrderMapper.INSTANCE.toOrder(orderDto);
        orderRepository.save(order);
        orderDto.setId(order.getId());

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (Integer product : products) {
            Product product1 = productRepository.findById(product).orElseThrow(
                    () -> new EntityNotFoundException("Product with id ="  + product + " wasn`t found")
            );
            ProductDto productDto1 = ProductMapper.INSTANCE.toProductDto(product1);
            orderItemDtos.add(addOrderItem(new OrderItemDto(null, 1,
                    productDto1,  orderDto)));
        }

        List<OrderItem> orderItems = orderItemDtos.stream()
                .map(o -> OrderItemMapper.INSTANCE.toOrderItem(o))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return null;
    }

    public OrderItemDto addOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = OrderItemMapper.INSTANCE.toOrderItem(orderItemDto);
        orderItemRepository.save(orderItem);
        orderItemDto.setId(orderItem.getId());
        return orderItemDto;
    }

    public ClientAndOrdersDto getClientAndOrdersById(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id ="  + clientId + " wasn`t found")
        );
        List<Order> orders = orderRepository.findAllByClient_Id(clientId);
        List<OrderDto> orderDtos = orders.stream()
                .map(OrderMapper.INSTANCE::toOrderDto)
                .collect(Collectors.toList());
        return new ClientAndOrdersDto(client.getId(), client.getName(),
                client.getEmail(), client.getPhone(),
                AddressMapper.INSTANCE.toAddressDto(client.getAddress()), orderDtos);
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
        return new ClientAndIdOrdersDto(client.getId(), client.getName(),
                client.getEmail(), client.getPhone(), ids);
    }

}
