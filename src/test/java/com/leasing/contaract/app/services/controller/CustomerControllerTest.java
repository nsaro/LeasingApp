package com.leasing.contaract.app.services.controller;

import com.leasing.contaract.app.controllers.CustomerController;
import com.leasing.contaract.app.services.CustomerService;
import com.leasing.contaract.generated.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.leasing.contaract.app.services.TestUtil.REST_BASE_PATH;
import static com.leasing.contaract.app.services.TestUtil.getCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void getCustomer_shouldReturnCustomerByGivenId() throws Exception {
        Customer customer = getCustomer();
        when(customerService.get(1L)).thenReturn(customer);
        this.mockMvc.perform(get(REST_BASE_PATH + "/customer/get/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value(customer.getBirthDate().toString()));
    }

    @Test
    void saveCustomer_shouldSaveCustomer() throws Exception {
        Customer customer = getCustomer();
        when(customerService.save(any())).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.post(REST_BASE_PATH + "/customer/save")
                .content("{\"firstName\":\"Neer\",\"lastName\":\"Kumar\",\"birthDate\": \"1989-02-28\"}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
