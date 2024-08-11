package com.naiomi.customer;

import io.micronaut.http.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerServiceImp customerService) {
        this.customerService = customerService;
    }

    @Get("/")
    public List<Customer> list() {
        return customerService.list();
    }

    @Get("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @Get("/status/{status}")
    public List<Customer> getCustomerByStatus(@PathVariable("status") String status) {
        return customerService.listByStatus(status);
    }

    @Post
    public Customer create(@Body Customer customer) {
        return customerService.create(customer);
    }

    @Put("/{id}")
    public Customer update(Long id, @Body Customer customer) {
        return customerService.update(id, customer);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        customerService.delete(id);
    }
}