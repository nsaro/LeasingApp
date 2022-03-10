package com.leasing.contaract.app.services.controller;

import com.leasing.contaract.app.controllers.ContractOverviewController;
import com.leasing.contaract.app.services.ContractOverviewService;
import com.leasing.contaract.generated.model.ContractOverview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static com.leasing.contaract.app.services.TestUtil.REST_BASE_PATH;
import static com.leasing.contaract.app.services.TestUtil.getContractOverview;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContractOverviewController.class)
class ContractOverviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractOverviewService contractOverviewService;

    @Test
    void getCustomer_shouldReturnCustomerByGivenId() throws Exception {
        //Arrange
        ContractOverview contractOverview = getContractOverview();
        when(contractOverviewService.getAll(1, 10)).thenReturn(Collections.singletonList(contractOverview));

        //Assert
        this.mockMvc.perform(get(REST_BASE_PATH + "/contractOverview/getAll?page=1&size=10")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contractNumber").value(contractOverview.getContractNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerName").value(contractOverview.getCustomerName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle").value(contractOverview.getVehicle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin").value(contractOverview.getVin()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].monthlyRate").value(contractOverview.getMonthlyRate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehiclePrice").value(contractOverview.getVehiclePrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId").value(contractOverview.getCustomerId()));
    }
}
