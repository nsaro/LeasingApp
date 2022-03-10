package com.leasing.contaract.app.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "vehicle")
    private LeasingContractEntity leasingContract;

    private String brand;
    private String model;
    private Integer year;
    private String identificationNumber;
    private Double price;

}
