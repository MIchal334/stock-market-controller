package com.stockMarket.controller.adapter.dto;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<IncidentEntity> incidentList;

}
