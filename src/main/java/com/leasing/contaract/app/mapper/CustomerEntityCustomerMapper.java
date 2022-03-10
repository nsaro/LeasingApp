package com.leasing.contaract.app.mapper;

import com.leasing.contaract.app.dao.entities.CustomerEntity;
import com.leasing.contaract.generated.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerEntityCustomerMapper {

    @Mapping(target = "leasingContracts", ignore = true)
    CustomerEntity customerToCustomerEntity(Customer source);

    Customer customerEntityToCustomer(CustomerEntity destination);
}
