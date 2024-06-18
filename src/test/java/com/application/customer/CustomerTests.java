package com.application.customer;

import com.application.address.Address;
import com.application.contact.Contact;
import com.application.customer.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        Customer customerOne = new Customer("1", "NAMEONE", "LASTONE", "ACTIVE", new Address(), new Contact());
        Customer customerTwo = new Customer("2", "NAMETWO", "LASTTWO", "ACTIVE", new Address(), new Contact());
        Customer customerThree = new Customer("3", "NAMETHREE", "LASTTHREE", "ACTIVE", new Address(), new Contact());
        Customer customerFour = new Customer("4", "NAMEFOUR", "LASTFOUR", "INACTIVE", new Address(), new Contact());

        customerRepository.save(customerOne);
        customerRepository.save(customerTwo);
        customerRepository.save(customerThree);
        customerRepository.save(customerFour);

    }

//    @Test
//    @DirtiesContext
//    public void verifyCustomerCount() {
//
//        List<Customer> customers = customerService.findAll();
//        assertEquals(4, customers.size());
//    }
//
//    @Test
//    @DirtiesContext
//    public void verifyCustomerStatusActive() {
//        List<Customer> customers = customerService.getCustomerByStatus("ACTIVE");
//        assert customers.size() == 3;
//    }
//
//    @Test
//    public void verifyCustomerStatusInactive() {
//        List<Customer> customers = customerService.getCustomerByStatus("INACTIVE");
//        assert customers.size() == 2;
//    }
//
//    @Test
//    public void testCustomerById() {
//        Customer customer = customerService.findById("1").get();
//        assert customer.getName().equals("NAMEONE");
//    }

    @Test
    public void testCustomerByIdNotFound() {
        Customer customer = customerService.findById("9").orElse(null);
        assert customer == null;
    }
}
