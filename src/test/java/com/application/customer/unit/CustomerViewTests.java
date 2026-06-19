package com.application.customer.unit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.application.customer.model.Customer;
import com.application.customer.service.CustomerService;
import com.application.customer.web.CustomerViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerViewTests {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerViewController customerViewController;

    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new BindingAwareModelMap();
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = Collections.singletonList(new Customer());
        when(customerService.findAll()).thenReturn(customers);

        // Act
        String viewName = customerViewController.getAllCustomers(model);

        // Assert
        assertEquals("customers", viewName);
        assertTrue(model.containsAttribute("customers"));
        assertEquals(customers, model.getAttribute("customers"));
    }

    @Test
    public void testNewCustomer() {
        // Act
        String viewName = customerViewController.newCustomer(model);

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
        String viewName = customerViewController.createCustomer(customer, model);

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
        String viewName = customerViewController.createCustomer(customer, model);

        // Assert
        assertEquals("redirect:/view/customers", viewName);
        verify(customerService, times(1)).createCustomer(customer);
    }
}
