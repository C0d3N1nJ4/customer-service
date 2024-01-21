package com.application.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.application.address.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaOperations;

import java.util.concurrent.CompletableFuture;

class KafkaCustomerTest {

    @Mock
    private KafkaOperations<String, String> kafkaOperations;
    @Mock
    private CustomerRepository customerRepository;

    private CustomerServiceImpl service;

    @Mock
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CustomerServiceImpl(kafkaOperations, customerRepository, addressService);
    }

    @Test
    void createCustomer_Success() {
        Customer customer = new Customer(); // create a test customer
        when(kafkaOperations.send(anyString(), anyString())).thenReturn(CompletableFuture.completedFuture(null));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = service.createCustomer(customer);

        // Verify interactions and assert results
        verify(kafkaOperations).send(eq("customer"), anyString());
        verify(customerRepository).save(customer);
        assertEquals(customer, result);
    }

    @Test
    void createCustomer_Failure() {
        Customer customer = new Customer(); // create a test customer
        when(kafkaOperations.send(anyString(), anyString())).thenReturn(CompletableFuture.failedFuture(new RuntimeException("Failed to send message")));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = service.createCustomer(customer);

        // Verify interactions and assert results
        verify(kafkaOperations).send(eq("customer"), anyString());
        verify(customerRepository, never()).save(customer);
        assertEquals(null, result);
    }
}
