package com.leasing.contaract.app.controllers;

import com.leasing.contaract.app.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leasing.contaract.generated.api.CustomerApi;
import com.leasing.contaract.generated.model.Customer;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Void> saveCustomer(@Valid @RequestBody Customer customer) {
        this.customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
