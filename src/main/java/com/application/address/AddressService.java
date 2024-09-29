package com.application.address;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    List<Address> findAll();

    Optional<Address> findById(String id);

    Address create(Address address);

    boolean existsById(String id);
}
