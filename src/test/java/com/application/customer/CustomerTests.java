package com.application.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
public class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DirtiesContext
    public void verifyCustomerCount() {

        List<Customer> customers = customerRepository.findAll();
        assert customers.size() == 4;
    }

    @Test
    public void verifyCustomerStatusActive() {
        List<Customer> customers = customerRepository.findCustomersByStatus("ACTIVE");
        assert customers.size() == 2;
    }

    @Test
    public void verifyCustomerStatusInactive() {
        List<Customer> customers = customerRepository.findCustomersByStatus("INACTIVE");
        assert customers.size() == 2;
    }

    @Test
    public void testCustomerById() {
        Customer customer = customerRepository.findById("1").get();
        assert customer.getName().equals("NAMEONE");
    }

    @Test
    public void testCustomerByIdNotFound() {
        Customer customer = customerRepository.findById("9").orElse(null);
        assert customer == null;
    }


}
