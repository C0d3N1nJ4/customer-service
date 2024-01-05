package com.application.customer;

import com.application.address.Address;
import com.application.address.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getCustomerByStatus(String status) {
        return customerRepository.findCustomersByStatus(status);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerByAddress_Id(String addressId) {
        return customerRepository.findCustomerByAddress_Id(addressId);
    }

    @Override
    public Customer saveCustomerWithAddress(Customer customer, String addressId) {

        Optional<Address> address = addressService.findById(addressId);

        if (customerRepository.existsById(customer.getId())) {
            throw new RuntimeException("Customer already exists");
        } else {
            if (address.isPresent()) {
                return customerRepository.save(customer);
            } else {
                throw new RuntimeException("Address not found");
            }
        }
    }


}
