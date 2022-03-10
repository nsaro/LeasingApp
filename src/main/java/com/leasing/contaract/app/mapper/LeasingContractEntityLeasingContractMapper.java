package com.leasing.contaract.app.mapper;

import com.leasing.contaract.app.dao.entities.LeasingContractEntity;
import com.leasing.contaract.generated.model.LeasingContract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface LeasingContractEntityLeasingContractMapper {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    LeasingContractEntity leasingContractToLeasingContractEntity(LeasingContract leasingContract);

    LeasingContract leasingContractEntityToLeasingContract(LeasingContractEntity leasingContractEntity);

    List<LeasingContract> leasingContractEntitiesToLeasingContracts(List<LeasingContractEntity> destination);
}
