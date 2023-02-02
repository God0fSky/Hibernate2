package com.example.hibernate2.repositories;

import com.example.hibernate2.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> { }
