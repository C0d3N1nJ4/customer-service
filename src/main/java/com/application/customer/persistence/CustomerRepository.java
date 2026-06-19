package com.application.customer.persistence;

import com.application.customer.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findAll();

    List<Customer> findCustomersByStatus(String status);

    Optional<Customer> findCustomerByAddress_Id(String addressId);
}
