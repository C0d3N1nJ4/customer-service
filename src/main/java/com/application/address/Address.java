package com.application.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    private String id;

    private String street;

    private String number;

    private String suburb;

    private String city;

    private String postalCode;

}
