package com.example.redis.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/api/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }

    @GetMapping(value = "/api/customers/{phoneNo}")
    public ResponseEntity<?> getCustomer(@PathVariable String phoneNo) {
        Customer foundCustomer = customerService.findCustomerByPhoneNo(phoneNo);

        if(foundCustomer == null) {
            throw new NullPointerException("Customer not exist");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundCustomer);
    }
}
