package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.CustomerEntity;
import com.leasing.contaract.app.dao.repository.CustomerRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.generated.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.leasing.contaract.app.services.TestUtil.getCustomer;
import static com.leasing.contaract.app.services.TestUtil.getCustomerEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getCustomerById_shouldReturnCustomer() {
        //Arrange
        CustomerEntity customerEntity = getCustomerEntity();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));

        //Act
        Customer customer = customerService.get(1L);

        //Assert
        Assertions.assertEquals( 1L, customer.getId());
    }
    @Test
    void getCustomerById_whenCustomerNotFound_shouldThrowException() {
        //Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementFoundException.class, () -> {
            customerService.get(1L);
        });
        String expectedMessage = "Customer not found";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveCustomer_shouldPersistCustomer() {
        //Arrange
        CustomerEntity customerEntity = getCustomerEntity();
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        //Act
        Customer persistedCustomer = customerService.save(getCustomer());

        //Assert
        Assertions.assertEquals(persistedCustomer.getId(), customerEntity.getId());
    }





}
