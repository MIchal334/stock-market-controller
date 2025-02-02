package com.stockMarket.controller.adapter.dto;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "action_type")
@Getter
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

    static ActionEntity of(String name){
        return new ActionEntity(name);
    }

}
