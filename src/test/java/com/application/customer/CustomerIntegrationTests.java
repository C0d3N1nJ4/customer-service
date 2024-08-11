package com.application.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CustomerIntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void getAllCustomersTest_StatusOK() throws Exception{
        mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getCustomerByIdTest_StatusOK() throws Exception{
        mockMvc.perform(get("/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getCustomerByIdTest_StatusNotFound() throws Exception{
        mockMvc.perform(get("/customers/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getCustomerByStatusTest_StatusOK() throws Exception{
        mockMvc.perform(get("/customers/filter/ACTIVE").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerByStatusTest_StatusBadRequest() throws Exception{
        mockMvc.perform(get("/customers/filter/status").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

//    @Test
//    public void saveCustomer_StatusCREATED() throws Exception{
//
//        this.mockMvc.perform(post("/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"id\":\"5\", \"name\":\"NAMEFIVE\", \"lastname\":\"LASTNAME\", \"status\": \"ACTIVE\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(content().json("""
//                                {
//                                    "id": "5",
//                                    "name": "NAMEFIVE",
//                                    "lastname": "LASTNAME",
//                                    "status": "ACTIVE"
//                                }"""));
//    }

//    @Test
//    public void saveCustomerWithAddress_StatusCREATED() throws Exception{
//
//        this.mockMvc.perform(post("/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"id\":\"6\", \"name\":\"NAMEFIVE\", \"lastname\":\"LASTNAME\", \"status\": \"ACTIVE\", \"address\": { \"id\": \"6\"}}"))
//                .andExpect(status().isCreated())
//                .andExpect(content().json("""
//                                {
//                                    "id": "6",
//                                    "name": "NAMEFIVE",
//                                    "lastname": "LASTNAME",
//                                    "status": "ACTIVE",
//                                    "address": {
//                                        "id": "6",
//                                        "street": "STREET NAME",
//                                        "number": "6",
//                                        "suburb": "SUBURBSIX",
//                                        "city": "VIENNA",
//                                        "postalCode": "1234"
//                                    }
//                                }"""));
//    }
}
