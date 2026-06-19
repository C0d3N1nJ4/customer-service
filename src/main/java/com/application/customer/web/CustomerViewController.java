package com.application.customer.web;

import com.application.customer.model.Customer;
import com.application.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/view/customers")
public class CustomerViewController {

    private final CustomerService customerService;

    public CustomerViewController(CustomerService customerService) {
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
            return "redirect:.";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") String id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);

        return "editCustomer";
    }

    @PostMapping("/update")
    public String updateCustomer(@Valid Customer customer, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "editCustomer";
        }

        customerService.updateCustomer(customer);
        return "redirect:.";
    }
}
