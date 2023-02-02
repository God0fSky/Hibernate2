package com.example.hibernate2.repositories;

import com.example.hibernate2.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByClient_Id(int clientId);
}
