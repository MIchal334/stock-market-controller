package com.stockMarket.controller.adapter.dto;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "action_type")
@Getter
class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_name")
    private String actionName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<IncidentEntity> incidentList;

}
