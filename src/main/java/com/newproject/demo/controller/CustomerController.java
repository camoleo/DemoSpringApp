package com.newproject.demo.controller;

import com.newproject.demo.model.Customer;
import com.newproject.demo.service.CustomerService;
import com.newproject.demo.service.PersonnelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hotel/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/hotel/customer")
    public void addClient(@RequestBody Customer customer) {
        log.info(customer.toString());
    }
}