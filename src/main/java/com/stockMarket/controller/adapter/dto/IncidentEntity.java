package com.stockMarket.controller.adapter.dto;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "incident")
@Getter
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "action_amount")
    private int actionAmount;

    @Column(name = "price_threshold")
    private float priceThreshold;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private ActionEntity action;

    @ManyToOne
    @JoinColumn(name = "customer_email")
    private CustomerEntity customer;

}
