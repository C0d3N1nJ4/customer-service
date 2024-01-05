package com.application.address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
    Iterable<Address> findAddressByCity(String city);
}
