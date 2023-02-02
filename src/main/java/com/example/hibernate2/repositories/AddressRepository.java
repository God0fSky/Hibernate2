package com.example.hibernate2.repositories;

import com.example.hibernate2.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
