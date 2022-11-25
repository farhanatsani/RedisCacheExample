package com.example.redis.customer.controller;

import com.example.redis.base.BaseController;
import com.example.redis.base.RspMsgConstants;
import com.example.redis.customer.dto.CustomerDTO;
import com.example.redis.customer.service.CustomerService;
import com.example.redis.customer.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController extends BaseController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping(value = "/api/customers")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerdto) {

        CustomerDTO customerSave = customerService.saveCustomer(customerdto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createResponse(customerSave, HttpStatus.OK.value(), RspMsgConstants.DATA_AVAILABLE));
    }
    @GetMapping(value = "/api/customers")
    public ResponseEntity<?> getCustomers() {

        List<CustomerDTO> customersDTO = customerService.findCustomers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createResponse(customersDTO, HttpStatus.OK.value(), RspMsgConstants.DATA_AVAILABLE));
    }
    @GetMapping(value = "/api/customers/{phoneNo}")
    public ResponseEntity<?> getCustomer(@PathVariable String phoneNo) {

        CustomerDTO customerByPhoneNo = customerService.findCustomerByPhoneNo(phoneNo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createResponse(customerByPhoneNo, HttpStatus.OK.value(), RspMsgConstants.DATA_AVAILABLE));
    }
}
