package com.leasing.contaract.app.services;

import com.leasing.contaract.generated.model.ContractOverview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.leasing.contaract.app.services.TestUtil.getLeasingContract;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractOverviewServiceTest {

    @InjectMocks
    private ContractOverviewService contractOverviewService;

    @Mock
    private LeasingContractService leasingContractService;

    @Test
    void getAllContractOverviews_shouldReturnContractOverviews() {
        //Arrange
        when(leasingContractService.getAll(1, 10)).thenReturn(Collections.singletonList(getLeasingContract()));

        //Act
        List<ContractOverview> contractOverviews = contractOverviewService.getAll(1, 10);

        //Assert
        Assertions.assertEquals(1, contractOverviews.size());
    }
}
