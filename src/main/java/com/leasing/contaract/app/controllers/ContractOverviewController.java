package com.leasing.contaract.app.controllers;

import com.leasing.contaract.app.services.ContractOverviewService;
import com.leasing.contaract.generated.api.ContractOverviewApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leasing.contaract.generated.model.ContractOverview;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractOverviewController implements ContractOverviewApi {

    private final ContractOverviewService contractOverviewService;

    ContractOverviewController(ContractOverviewService contractOverviewService) {
        this.contractOverviewService = contractOverviewService;
    }

    @Override
    public ResponseEntity<List<ContractOverview>> getAllContractOverviews(@RequestParam(value = "page") Integer page,
                                                                          @RequestParam(value = "size") Integer size) {
        List<ContractOverview> contractOverviews = this.contractOverviewService.getAll(page,size);
        return ResponseEntity.ok(contractOverviews);
    }
}
