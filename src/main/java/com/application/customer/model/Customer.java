package com.application.customer.model;

import com.application.address.model.Address;
import com.application.contact.model.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String status;

    @OneToOne
    private Address address;

    @OneToOne
    private Contact contact;

}