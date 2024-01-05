package com.application.address;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AddressService {

    Optional<Address> findById(String id);

    Address create(Address address);

    boolean existsById(String id);
}
