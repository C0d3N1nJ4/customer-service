package com.application.address.persistence;

import com.application.address.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    Iterable<Address> findAddressByCity(String city);

    List<Address> findAll();
}
