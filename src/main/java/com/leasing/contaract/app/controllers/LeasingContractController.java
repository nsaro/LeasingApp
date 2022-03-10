package com.leasing.contaract.app.controllers;

import com.leasing.contaract.app.services.LeasingContractService;
import com.leasing.contaract.generated.api.LeasingContractApi;
import com.leasing.contaract.generated.model.LeasingContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LeasingContractController implements LeasingContractApi {

    private final LeasingContractService leasingContractService;

    public LeasingContractController(LeasingContractService leasingContractService) {
        this.leasingContractService = leasingContractService;
    }

    @Override
    public ResponseEntity<LeasingContract> getLeasingContract(@PathVariable("id") Long id) {
        LeasingContract leasingContract = this.leasingContractService.get(id);
        return ResponseEntity.ok(leasingContract);
    }

    @Override
    public ResponseEntity<Void> saveLeasingContract(@RequestBody LeasingContract leasingContract) {
        this.leasingContractService.save(leasingContract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
