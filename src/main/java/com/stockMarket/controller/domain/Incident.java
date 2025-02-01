package com.stockMarket.controller.domain;

public record Incident(String companyName, float priceThreshold, ActionName action, int actionAmount) {
}
