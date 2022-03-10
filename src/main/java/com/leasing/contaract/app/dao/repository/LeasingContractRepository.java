package com.leasing.contaract.app.dao.repository;

import com.leasing.contaract.app.dao.entities.LeasingContractEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeasingContractRepository extends PagingAndSortingRepository<LeasingContractEntity, Integer> {
    Optional<LeasingContractEntity> findById(Long id);
}
