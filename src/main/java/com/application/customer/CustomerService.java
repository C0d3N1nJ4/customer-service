package com.application.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    Iterable<Customer> findAll();

    boolean existsById(String id);

    Optional<Customer> findById(String id);

    List<Customer> getCustomerByStatus(String status);

    Customer createCustomer(Customer customer);

    Optional<Customer> findCustomerByAddress_Id(String addressId);

    Customer saveCustomerWithAddress(Customer customer, String addressId);
}
