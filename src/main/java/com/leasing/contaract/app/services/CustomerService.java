package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.CustomerEntity;
import com.leasing.contaract.app.dao.repository.CustomerRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.app.mapper.CustomerEntityCustomerMapper;
import com.leasing.contaract.generated.model.Customer;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEntityCustomerMapper mapper = Mappers.getMapper(CustomerEntityCustomerMapper.class);

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer get(Long id) {
        CustomerEntity customerEntity = this.customerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementFoundException("Customer not found"));
        return mapper.customerEntityToCustomer(customerEntity);
    }

    public Customer save(Customer customer) {
        CustomerEntity customerEntity = mapper.customerToCustomerEntity(customer);
        CustomerEntity persistedEntity = this.customerRepository.save(customerEntity);
        return mapper.customerEntityToCustomer(persistedEntity);
    }

}
