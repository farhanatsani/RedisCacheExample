package com.example.redis.customer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Getter @Setter @ToString
@SuperBuilder
@RequiredArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Integer id;
    private String name;
    private String phoneNo;
    private String address;
}
