package com.example.redis.customer.mapper;

import com.example.redis.customer.dto.CustomerDTO;
import com.example.redis.customer.entity.Customer;

public class CustomerMapper {
    private CustomerMapper() {}
    public static CustomerDTO toCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .customerName(customer.getName())
                .phoneNo(customer.getPhoneNo())
                .address(customer.getAddress())
                .build();
    }

    public static Customer toCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getCustomerName())
                .phoneNo(customerDTO.getPhoneNo())
                .address(customerDTO.getAddress())
                .build();
    }
}
