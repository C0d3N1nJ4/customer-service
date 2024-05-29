package com.application.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/view/customers")
public class CustomerViewService {

    private final CustomerServiceImpl customerService;

    public CustomerViewService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        Iterable<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "createCustomer";
    }

    @PostMapping("/new")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        Optional<Customer> existingCustomer = customerService.findById(customer.getId());
        if (existingCustomer.isPresent()) {
            model.addAttribute("error", "Customer ID already exists. Please input another ID.");
            return "createCustomer";
        } else {
            customerService.createCustomer(customer);
            return "redirect:/view/customers";
        }
    }
}
