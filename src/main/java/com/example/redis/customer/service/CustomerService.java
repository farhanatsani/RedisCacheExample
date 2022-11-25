package com.example.redis.customer.service;

import com.example.redis.customer.dto.CustomerDTO;
import com.example.redis.customer.entity.Customer;
import com.example.redis.customer.mapper.CustomerMapper;
import com.example.redis.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public CustomerDTO findCustomerByPhoneNo(String phoneNo) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNo(phoneNo);
        if(customerOptional.isEmpty()) {
            throw new NullPointerException("Customer not found");
        }
        Customer customer = customerOptional.get();
        return CustomerMapper.toCustomerDTO(customer);
    }
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNo(customerDTO.getPhoneNo());
        if(customerOptional.isPresent()) {
            throw new EntityExistsException("Customer already exist");
        }

        log.info("customerDTO {}", customerDTO.toString());

        Customer customerSave = customerRepository
                .save(CustomerMapper.toCustomer(customerDTO));
        return CustomerMapper.toCustomerDTO(customerSave);
    }
    public List<CustomerDTO> findCustomers() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> allCustomerDTO = customers.stream()
                .map(customer -> CustomerMapper.toCustomerDTO(customer))
                .collect(Collectors.toList());

        return allCustomerDTO;
    }
}
