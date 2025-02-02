package com.stockMarket.controller.domain;

public record Incident(Long incidentId, String companyName, float priceThreshold, ActionName action, int actionAmount) {
}
