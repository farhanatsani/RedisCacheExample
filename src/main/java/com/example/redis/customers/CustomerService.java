package com.example.redis.customers;

import com.example.redis.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Slf4j
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerByPhoneNo(String phoneNo) {
        return customerRepository.findByPhoneNo(phoneNo);
    }

    public void saveCustomer(Customer customer) {
        Customer existCustomer = findCustomerByPhoneNo(customer.getPhoneNo());
        if(existCustomer != null) {
            throw new EntityExistsException("Customer already exist");
        }

        customerRepository.save(customer);
    }

}
