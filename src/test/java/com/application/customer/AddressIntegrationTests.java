package com.application.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
public class AddressIntegrationTests {

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
    public void getAddressById_StatusOK() throws Exception{
        mockMvc.perform(get("/address/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAddressById_StatusNotFound() throws Exception{
        mockMvc.perform(get("/address/66").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAddressByCity_StatusOK() throws Exception{
        mockMvc.perform(get("/address/filter/city/Chicago").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAddressByCity_StatusOK_EmptyList() throws Exception{
        mockMvc.perform(get("/address/filter/city/TEST").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void saveAddress_StatusCREATED() throws Exception{
        this.mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "6",
                                    "street": "STREETSIX",
                                    "number": "6",
                                    "suburb": "PAULSHOF",
                                    "city": "SANDTON",
                                    "postalCode": "1234"
                                }"""))
                .andExpect(status().isCreated());
//                .andExpect(content().json("""
//                                {
//                                    "id": "6",
//                                    "street": "STREETSIX",
//                                    "number": "6",
//                                    "suburb": "PAULSHOF",
//                                    "city": "SANDTON",
//                                    "postalCode": "1234"
//                                }"""));

    }
}
