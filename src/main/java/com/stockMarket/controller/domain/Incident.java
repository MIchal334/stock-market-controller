package com.stockMarket.controller.domain;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockMarket.controller.adapter.dto.IncidentEntity;

public record Incident(String clientEmail, String companyName, float priceThreshold, ActionName action,
                       int actionAmount, CheckType checkType) {
    static private final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Incident", e);
        }
    }

    public static Incident of(IncidentEntity incidentEntity, String email) {
        return new Incident(email, incidentEntity.getCompanyName(), incidentEntity.getPriceThreshold(),
                ActionName.valueOf(incidentEntity.getAction().getActionName()), incidentEntity.getActionAmount(),
                CheckType.fromSymbol(incidentEntity.getCompereSing()));
    }

}
