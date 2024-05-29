package com.application.customer;

import com.application.address.Address;
import com.application.contact.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
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