package com.application.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    Iterable<Address> findAddressByCity(String city);

    List<Address> findAll();
}
