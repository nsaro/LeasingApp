package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.VehicleEntity;
import com.leasing.contaract.app.dao.repository.VehicleRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.generated.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.leasing.contaract.app.services.TestUtil.getVehicle;
import static com.leasing.contaract.app.services.TestUtil.getVehicleEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Test
    void getVehicleById_shouldReturnVehicle() {
        //Arrange
        VehicleEntity vehicleEntity = getVehicleEntity();
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicleEntity));

        //Act
        Vehicle vehicle = vehicleService.get(1L);

        //Assert
        Assertions.assertEquals( 1L, vehicle.getId());
    }

    @Test
    void getVehicleById_whenVehicleNotFound_shouldThrowException() {
        //Arrange
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementFoundException.class, () -> {
            vehicleService.get(1L);
        });
        String expectedMessage = "Vehicle not found";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveVehicle_shouldPersistVehicle() {
        //Arrange
        VehicleEntity vehicleEntity = getVehicleEntity();
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(vehicleEntity);

        //Act
        Vehicle persistedVehicle = vehicleService.save(getVehicle());

        //Assert
        Assertions.assertEquals(persistedVehicle.getId(), vehicleEntity.getId());
    }

}
