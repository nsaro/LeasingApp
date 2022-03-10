package com.leasing.contaract.app.controllers;

import com.leasing.contaract.app.services.VehicleService;
import com.leasing.contaract.generated.api.VehicleApi;
import com.leasing.contaract.generated.model.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VehicleController implements VehicleApi {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") Long id) {
        Vehicle customer = this.vehicleService.get(id);
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Void> saveVehicle(@RequestBody Vehicle vehicle) {
        this.vehicleService.save(vehicle);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
