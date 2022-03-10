package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.CustomerEntity;
import com.leasing.contaract.app.dao.entities.LeasingContractEntity;
import com.leasing.contaract.app.dao.entities.VehicleEntity;
import com.leasing.contaract.generated.model.Customer;
import com.leasing.contaract.generated.model.LeasingContract;
import com.leasing.contaract.generated.model.Vehicle;
import com.leasing.contaract.generated.model.ContractOverview;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

public class TestUtil {
    public static final String REST_BASE_PATH = "/api";

    public static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setBirthDate(LocalDate.of(1989, 2, 28));
        customer.setFirstName("Max");
        customer.setLastName("Mueller");
        return customer;
    }

    public static Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("BMW");
        vehicle.setIdentificationNumber("XCVO001");
        vehicle.setModel("X3");
        vehicle.setPrice(50000f);
        vehicle.setYear(2020);
        return vehicle;
    }

    public static CustomerEntity getCustomerEntity() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setBirthDate(new Date());
        customerEntity.setFirstName("Max");
        customerEntity.setLastName("Mueller");
        customerEntity.setLeasingContracts(Collections.emptyList());
        return customerEntity;
    }

    public static VehicleEntity getVehicleEntity() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(1L);
        vehicleEntity.setBrand("BMW");
        vehicleEntity.setIdentificationNumber("XCVO001");
        vehicleEntity.setModel("X3");
        vehicleEntity.setPrice(50000.0);
        vehicleEntity.setYear(2020);
        return vehicleEntity;
    }

    public static LeasingContractEntity getLeasingContractEntity() {
        LeasingContractEntity leasingContractEntity = new LeasingContractEntity();
        leasingContractEntity.setId(1L);
        leasingContractEntity.setContractNumber(12345678L);
        leasingContractEntity.setMonthlyRate(1200.0f);
        return leasingContractEntity;
    }

    public static LeasingContract getLeasingContract() {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber(12345678L);
        leasingContract.setMonthlyRate(1200.0f);
        leasingContract.setCustomer(getCustomer());
        leasingContract.setVehicle(getVehicle());
        return leasingContract;
    }

    public static ContractOverview getContractOverview() {
        ContractOverview contractOverview =  new ContractOverview();
        contractOverview.setCustomerId(1L);
        contractOverview.setVehiclePrice(222000f);
        contractOverview.setVin("XUNCBF112");
        contractOverview.setContractNumber(1234567L);
        contractOverview.setCustomerName("Neer Kumar");
        contractOverview.setMonthlyRate(12220f);
        contractOverview.setVehicle("Bmw X3");
        return contractOverview;
    }
}
