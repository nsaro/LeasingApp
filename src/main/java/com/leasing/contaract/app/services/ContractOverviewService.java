package com.leasing.contaract.app.services;

import com.leasing.contaract.generated.model.Customer;
import com.leasing.contaract.generated.model.Vehicle;
import org.springframework.stereotype.Service;
import com.leasing.contaract.generated.model.ContractOverview;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractOverviewService {

    private final LeasingContractService leasingContractService;

    public ContractOverviewService(LeasingContractService leasingContractService) {
        this.leasingContractService = leasingContractService;
    }

    public List<ContractOverview> getAll(int page, int size) {
        List<ContractOverview> contractOverviews = new ArrayList<>();
        this.leasingContractService.getAll(page, size).forEach(leasingContract -> {
            ContractOverview contractOverview = new ContractOverview();
            Vehicle vehicle = leasingContract.getVehicle();
            Customer customer = leasingContract.getCustomer();
            contractOverview.setContractNumber(leasingContract.getContractNumber());
            contractOverview.setCustomerName(customer.getFirstName() +
                    " " + customer.getLastName());
            contractOverview.setVehicle(vehicle.getBrand() +
                    " " + vehicle.getModel() + " (" + vehicle.getYear() + ")");
            contractOverview.setVin(vehicle.getIdentificationNumber());
            contractOverview.setMonthlyRate(leasingContract.getMonthlyRate());
            contractOverview.setVehiclePrice(vehicle.getPrice());
            contractOverview.setCustomerId(customer.getId());
            contractOverviews.add(contractOverview);
        });
        return contractOverviews;
    }
}
