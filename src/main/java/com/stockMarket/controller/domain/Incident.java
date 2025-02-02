package com.stockMarket.controller.domain;


import com.stockMarket.controller.adapter.dto.IncidentEntity;

public record Incident(String companyName, float priceThreshold, ActionName action, int actionAmount) {

    public static Incident of(IncidentEntity incidentEntity) {
        return new Incident(incidentEntity.getCompanyName(), incidentEntity.getPriceThreshold(),
                ActionName.valueOf(incidentEntity.getAction().getActionName()), incidentEntity.getActionAmount());
    }

}
