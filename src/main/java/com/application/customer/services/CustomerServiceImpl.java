package com.application.customer.services;

import com.application.address.Address;
import com.application.address.AddressService;
import com.application.customer.Customer;
import com.application.customer.CustomerRepository;
import com.application.exceptions.AddressNotFoundException;
import com.application.exceptions.CustomerExistsException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    @Override
    public List<Customer> findAll() {
        log.info("Finding all customers");
        return customerRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        log.info("Checking if customer with id: " + id + " exists");
        return customerRepository.existsById(id);
    }

    @Override
    public Optional<Customer> findById(String id) {
        log.info("Finding customer with id: " + id);
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getCustomerByStatus(String status) {
        log.info("Getting customers with status: " + status);
        return customerRepository.findCustomersByStatus(status);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        checkIfCustomerExists(customer.getId());
        log.info("Creating customer: " + customer);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerByAddress_Id(String addressId) {
        log.info("Finding customer with address id: " + addressId);
        return customerRepository.findCustomerByAddress_Id(addressId);
    }

    @Override
    public Customer saveCustomerWithAddress(Customer customer, String addressId) {
        checkIfCustomerExists(customer.getId());

        Address address = addressService.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        customer.setAddress(address);
        log.info("Saving customer: " + customer + " with address: " + address);
        return customerRepository.save(customer);
    }

    private void checkIfCustomerExists(String customerId) {
        if (customerRepository.existsById(customerId)) {
            throw new CustomerExistsException(customerId);
        }
    }
}
