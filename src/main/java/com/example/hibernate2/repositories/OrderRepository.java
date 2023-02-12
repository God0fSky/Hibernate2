package com.example.hibernate2.repositories;

import com.example.hibernate2.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByClient_Id(int clientId);
}
