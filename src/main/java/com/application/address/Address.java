package com.application.address;

import com.application.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    private String id;

    private String street;

    private String number;

    private String suburb;

    private String city;

    private String postalCode;

}
