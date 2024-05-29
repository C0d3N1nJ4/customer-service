package com.application.customer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.Collections;
import java.util.Optional;

public class CustomerViewTests {

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerViewService customerViewService;

    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        Iterable<Customer> customers = Collections.singletonList(new Customer());
        when(customerService.findAll()).thenReturn(customers);

        // Act
        String viewName = customerViewService.getAllCustomers(model);

        // Assert
        assertEquals("customers", viewName);
        assertTrue(model.containsAttribute("customers"));
        assertEquals(customers, model.getAttribute("customers"));
    }

    @Test
    public void testNewCustomer() {
        // Act
        String viewName = customerViewService.newCustomer(model);

        // Assert
        assertEquals("createCustomer", viewName);
        assertTrue(model.containsAttribute("customer"));
        assertNotNull(model.getAttribute("customer"));
    }

    @Test
    public void testCreateCustomerWithExistingId() {
        // Arrange
        Customer customer = new Customer();
        customer.setId("1");
        when(customerService.findById("1")).thenReturn(Optional.of(customer));

        // Act
        String viewName = customerViewService.createCustomer(customer, model);

        // Assert
        assertEquals("createCustomer", viewName);
        assertTrue(model.containsAttribute("error"));
        assertEquals("Customer ID already exists. Please input another ID.", model.getAttribute("error"));
    }

    @Test
    public void testCreateCustomerWithNewId() {
        // Arrange
        Customer customer = new Customer();
        customer.setId("2");
        when(customerService.findById("2")).thenReturn(Optional.empty());

        // Act
        String viewName = customerViewService.createCustomer(customer, model);

        // Assert
        assertEquals("redirect:/view/customers", viewName);
        verify(customerService, times(1)).createCustomer(customer);
    }
}
