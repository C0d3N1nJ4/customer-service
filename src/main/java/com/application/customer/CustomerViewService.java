package com.application.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/view/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") String id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);

        return "edit";
    }

    @PostMapping("/customers/update")
    public String updateCustomer(@Valid Customer customer, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "edit";
        }

        customerService.createCustomer(customer);
        return "redirect:/view/customers";
    }
}
