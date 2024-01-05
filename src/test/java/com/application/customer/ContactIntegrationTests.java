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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ContactIntegrationTests {

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
    public void getAllContactsTest_StatusOK() throws Exception{
        mockMvc.perform(get("/contact").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getContactById_StatusOK() throws Exception{
        mockMvc.perform(get("/contact/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getContactById_StatusNotFound() throws Exception{
        mockMvc.perform(get("/contact/66").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void saveContact_StatusCREATED() throws Exception{
        this.mockMvc.perform(post("/contact")
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
    }
}
