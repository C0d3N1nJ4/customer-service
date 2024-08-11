package com.application.customer;

import com.application.address.Address;
import com.application.contact.Contact;
import com.application.customer.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Customer customerOne = new Customer("1", "NAMEONE", "LASTONE", "ACTIVE", new Address(), new Contact());
        Customer customerTwo = new Customer("2", "NAMETWO", "LASTTWO", "ACTIVE", new Address(), new Contact());
        Customer customerThree = new Customer("3", "NAMETHREE", "LASTTHREE", "ACTIVE", new Address(), new Contact());
        Customer customerFour = new Customer("4", "NAMEFOUR", "LASTFOUR", "INACTIVE", new Address(), new Contact());

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customerOne, customerTwo, customerThree, customerFour));
        when(customerRepository.findById("1")).thenReturn(Optional.of(customerOne));
        when(customerRepository.findById("2")).thenReturn(Optional.of(customerTwo));
        when(customerRepository.findById("3")).thenReturn(Optional.of(customerThree));
        when(customerRepository.findById("4")).thenReturn(Optional.of(customerFour));
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
    }

//TODO: Fix test
//    @Test
//    public void verifyCustomerCount() {
//        List<Customer> customers = customerService.findAll();
//        assertNotNull(customers);
//        assertEquals(4, customers.size());
//    }


//    @Test
//    public void verifyCustomerStatusActive() {
//        List<Customer> customers = customerService.getCustomerByStatus("ACTIVE");
//        assertEquals(3, customers.size());
//    }


//    @Test
//    public void verifyCustomerStatusInactive() {
//        List<Customer> customers = customerService.getCustomerByStatus("INACTIVE");
//        assertEquals(1, customers.size());
//    }

    @Test
    public void testCustomerById() {
        Optional<Customer> customer = customerService.findById("1");
        customer.ifPresent(value -> assertEquals("1", value.getId()));
    }

    @Test
    public void testCustomerByIdNotFound() {
        Customer customer = customerService.findById("9").orElse(null);
        assertNull(customer);
    }
}
