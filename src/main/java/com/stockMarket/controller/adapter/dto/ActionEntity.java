package com.stockMarket.controller.adapter.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "action_type")
@Getter
@NoArgsConstructor
public class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_name")
    private String actionName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<IncidentEntity> incidentList;

    public ActionEntity(String actionName) {
        this.actionName = actionName;
    }


}
