package com.application.customer;


import com.application.customer.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create/customer")
public class CustomerCreateController {

    private final CustomerService customerService;

    public CustomerCreateController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new customer record", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)))
    })
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping("address/{address-id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new customer record with address", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)))
    })
    public Customer saveCustomerWithAddress(@RequestBody Customer customer, @PathVariable("address-id") String addressId) {
        return customerService.saveCustomerWithAddress(customer, addressId);
    }
}
