package com.leasing.contaract.app.mapper;

import com.leasing.contaract.app.dao.entities.VehicleEntity;
import com.leasing.contaract.generated.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VehicleEntityVehicleMapper {

    @Mapping(target = "leasingContract", ignore = true)
    VehicleEntity vehicleToVehicleEntity(Vehicle source);

    Vehicle vehicleEntityToVehicle(VehicleEntity destination);
}
