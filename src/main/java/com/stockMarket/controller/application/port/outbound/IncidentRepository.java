package com.stockMarket.controller.application.port.outbound;

import com.stockMarket.controller.domain.Incident;

import java.util.List;

public interface IncidentRepository {

    void addNewIncidentToCheck(String userEmail, Incident incident);

    List<Incident> getAllIncidentByEmail(String email);

    void deleteIncidentById(Long id);
}
