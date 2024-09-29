package com.application.contact;

import com.application.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    private String id;

    private String email;

    private String phone;
}
