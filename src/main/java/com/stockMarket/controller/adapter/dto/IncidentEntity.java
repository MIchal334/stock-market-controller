package com.stockMarket.controller.adapter.dto;


import com.stockMarket.controller.domain.Incident;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incident")
@Getter
@NoArgsConstructor
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

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "compere_sing")
    private String compereSing;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private ActionEntity action;

    public IncidentEntity(String companyName, int actionAmount, float priceThreshold, String customerEmail, ActionEntity action, String compereSing) {
        this.companyName = companyName;
        this.actionAmount = actionAmount;
        this.priceThreshold = priceThreshold;
        this.customerEmail = customerEmail;
        this.action = action;
        this.compereSing = compereSing;

    }


    public static IncidentEntity of(String email, Incident incident, ActionEntity action) {
        return new IncidentEntity(incident.companyName(), incident.actionAmount(), incident.priceThreshold(), email, action, incident.checkType().getSymbol());
    }

}
