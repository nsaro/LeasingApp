package com.leasing.contaract.app.services.controller;

import com.leasing.contaract.app.controllers.LeasingContractController;
import com.leasing.contaract.app.services.LeasingContractService;
import com.leasing.contaract.generated.model.LeasingContract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.leasing.contaract.app.services.TestUtil.REST_BASE_PATH;
import static com.leasing.contaract.app.services.TestUtil.getLeasingContract;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeasingContractController.class)
class LeasingContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeasingContractService leasingContractService;

    @Test
    void getLeasingContract_shouldReturnLeasingContractByGivenId() throws Exception {
        LeasingContract leasingContract = getLeasingContract();
        when(leasingContractService.get(1L)).thenReturn(leasingContract);
        this.mockMvc.perform(get(REST_BASE_PATH + "/leasingContract/get/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contractNumber").value(leasingContract.getContractNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyRate").value(leasingContract.getMonthlyRate()));
    }

    @Test
    void saveLeasingContract_shouldSaveLeasingContract() throws Exception {
        LeasingContract leasingContract = getLeasingContract();
        when(leasingContractService.save(any())).thenReturn(leasingContract);
        mockMvc.perform(MockMvcRequestBuilders.post(REST_BASE_PATH + "/leasingContract/save")
                .content("{\"contractNumber\":1234567,\"monthlyRate\":20.10,\"customer\": {\"firstName\":\"Neer\"," +
                        "\"lastName\":\"Kumar\",\"birthDate\": \"1989-02-28\"},\"vehicle\": {\"brand\":\"Audi\",\"" +
                        "model\":\"Audi R8\",\"year\": \"2022\",\"identificationNumber\": \"XDFCZ11002\",\"price\":\"222000\"}}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
