package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.VehicleEntity;
import com.leasing.contaract.app.dao.repository.VehicleRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.app.mapper.VehicleEntityVehicleMapper;
import com.leasing.contaract.generated.model.Vehicle;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleEntityVehicleMapper mapper = Mappers.getMapper(VehicleEntityVehicleMapper.class);

    VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle get(Long id) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementFoundException("Vehicle not found"));
        return mapper.vehicleEntityToVehicle(vehicleEntity);
    }

    public Vehicle save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = mapper.vehicleToVehicleEntity(vehicle);
        VehicleEntity persistedEntity = this.vehicleRepository.save(vehicleEntity);
        return mapper.vehicleEntityToVehicle(persistedEntity);
    }

}
