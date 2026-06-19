package com.application.address.service;

import com.application.address.model.Address;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    List<Address> findAll();

    Optional<Address> findById(String id);

    Iterable<Address> findByCity(String city);

    Address create(Address address);

    boolean existsById(String id);
}
