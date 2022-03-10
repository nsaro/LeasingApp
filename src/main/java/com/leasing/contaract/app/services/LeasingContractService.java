package com.leasing.contaract.app.services;

import com.leasing.contaract.app.dao.entities.LeasingContractEntity;
import com.leasing.contaract.app.dao.repository.LeasingContractRepository;
import com.leasing.contaract.app.exceptions.NoSuchElementFoundException;
import com.leasing.contaract.app.mapper.LeasingContractEntityLeasingContractMapper;
import com.leasing.contaract.generated.model.LeasingContract;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasingContractService {

    private final LeasingContractRepository leasingContractRepository;
    private final LeasingContractEntityLeasingContractMapper mapper =
            Mappers.getMapper(LeasingContractEntityLeasingContractMapper.class);

    LeasingContractService(LeasingContractRepository leasingContractRepository) {
        this.leasingContractRepository = leasingContractRepository;
    }

    public LeasingContract get(Long id) {
        LeasingContractEntity leasingContractEntity = this.leasingContractRepository.findById(id).orElseThrow(
                () -> new NoSuchElementFoundException("LeasingContract not found"));
        return mapper.leasingContractEntityToLeasingContract(leasingContractEntity);
    }

    public LeasingContract save(LeasingContract leasingContract) {
        LeasingContractEntity leasingContractEntity = mapper.leasingContractToLeasingContractEntity(leasingContract);
        LeasingContractEntity persistedEntity = this.leasingContractRepository.save(leasingContractEntity);
        return mapper.leasingContractEntityToLeasingContract(persistedEntity);
    }

    public List<LeasingContract> getAll(int page, int size) {
        Page<LeasingContractEntity> leasingContractPages = this.leasingContractRepository.findAll(PageRequest.of(page, size));
        List<LeasingContractEntity> leasingContractEntities = leasingContractPages.get().collect(Collectors.toList());
        return mapper.leasingContractEntitiesToLeasingContracts(leasingContractEntities);
    }

}
