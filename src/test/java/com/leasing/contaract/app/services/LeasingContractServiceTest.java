package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.LeasingContractEntity;
import com.leasing.contaract.app.dao.repository.LeasingContractRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.generated.model.LeasingContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.leasing.contaract.app.services.TestUtil.getLeasingContract;
import static com.leasing.contaract.app.services.TestUtil.getLeasingContractEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeasingContractServiceTest {

    @InjectMocks
    private LeasingContractService leasingContractService;

    @Mock
    private LeasingContractRepository leasingContractRepository;

    @Test
    void getLeasingContractById_shouldReturnLeasingContract() {
        //Arrange
        LeasingContractEntity leasingContractEntity = getLeasingContractEntity();
        when(leasingContractRepository.findById(1L)).thenReturn(Optional.of(leasingContractEntity));

        //Act
        LeasingContract leasingContract = leasingContractService.get(1L);

        //Assert
        Assertions.assertEquals(1L, leasingContract.getId());
    }

    @Test
    void getAllLeasingContract_shouldReturnListOfLeasingContract() {
        //Arrange
        LeasingContractEntity leasingContractEntity = getLeasingContractEntity();
        List<LeasingContractEntity> leasingContractEntities = new ArrayList<>();
        leasingContractEntities.add(leasingContractEntity);
        Page<LeasingContractEntity> pagedTasks = new PageImpl<>(leasingContractEntities);
        when(leasingContractRepository.findAll(PageRequest.of(1, 10))).thenReturn(pagedTasks);

        //Act
        List<LeasingContract> leasingContracts = leasingContractService.getAll(1, 10);

        //Assert
        Assertions.assertEquals(1, leasingContracts.size());
    }

    @Test
    void getLeasingContractById_whenLeasingContractNotFound_shouldThrowException() {
        //Arrange
        when(leasingContractRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementFoundException.class, () -> {
            leasingContractService.get(1L);
        });
        String expectedMessage = "LeasingContract not found";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveLeasingContract_shouldPersistLeasingContract() {
        //Arrange
        LeasingContractEntity leasingContractEntity = getLeasingContractEntity();
        when(leasingContractRepository.save(any(LeasingContractEntity.class))).thenReturn(leasingContractEntity);

        //Act
        LeasingContract persistedLeasingContract = leasingContractService.save(getLeasingContract());

        //Assert
        Assertions.assertEquals(persistedLeasingContract.getId(), leasingContractEntity.getId());
    }

}
