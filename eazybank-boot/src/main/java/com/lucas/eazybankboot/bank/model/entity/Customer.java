package com.lucas.eazybankboot.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private String name;
    private String email;
    private String mobileNumber;

    @JsonIgnore
    private String pwd;

    private String role;
    private String createDt;

}
