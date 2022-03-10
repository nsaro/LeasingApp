package com.leasing.contaract.app.services.controller;

import com.leasing.contaract.app.controllers.VehicleController;
import com.leasing.contaract.app.services.VehicleService;
import com.leasing.contaract.generated.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.leasing.contaract.app.services.TestUtil.REST_BASE_PATH;
import static com.leasing.contaract.app.services.TestUtil.getVehicle;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    void getVehicle_shouldReturnVehicleByGivenId() throws Exception {
        Vehicle vehicle = getVehicle();
        when(vehicleService.get(1L)).thenReturn(vehicle);
        this.mockMvc.perform(get(REST_BASE_PATH + "/vehicle/get/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identificationNumber").value(vehicle.getIdentificationNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(vehicle.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(vehicle.getYear()));
    }

    @Test
    void saveVehicle_shouldSaveVehicle() throws Exception {
        Vehicle vehicle = getVehicle();
        when(vehicleService.save(any())).thenReturn(vehicle);
        mockMvc.perform(MockMvcRequestBuilders.post(REST_BASE_PATH + "/vehicle/save")
                .content("{\"brand\":\"Audi\",\"model\":\"Audi R8\",\"year\": \"2022\",\"identificationNumber\": \"XDFCZ11002\",\"price\":\"222000\"}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
