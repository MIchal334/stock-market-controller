package com.stockMarket.controller.application;

import com.stockMarket.controller.adapter.outbound.DBIncidentRepository;
import com.stockMarket.controller.domain.Incident;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockMarketService {
    public static final String DEFAULT_EMAIL = "test@wp.pl";
    public final DBIncidentRepository dbIncidentRepository;

    public StockMarketService(DBIncidentRepository dbIncidentRepository) {
        this.dbIncidentRepository = dbIncidentRepository;
    }

    public void addNewIncident(Incident incident) {
        dbIncidentRepository.addNewIncidentToCheck(
                DEFAULT_EMAIL,
                incident);
    }


    public void removeIncidentById(Long id) {
        this.dbIncidentRepository.deleteIncidentById(id);
    }

    public List<Incident> getAllIncidentByEmail(String email) {
        return this.dbIncidentRepository.getAllIncidentByEmail(email);
    }


}
