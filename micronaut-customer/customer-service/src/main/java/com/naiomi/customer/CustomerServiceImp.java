package com.naiomi.customer;

import com.naiomi.exceptions.CustomerNotFoundException;
import com.naiomi.exceptions.StatusNotFoundException;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @Override
    public @NonNull Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        return customerRepository.update(customer);
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> listByStatus(String status) {
        if (status == null) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Status parameter is required");
        } else if (status.equals("ACTIVE") || status.equals("INACTIVE")) {
            return customerRepository.findAllByStatus(status);
        } else {
            throw new StatusNotFoundException(status);
        }
    }
}