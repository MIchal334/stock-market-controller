package com.stockMarket.controller.application;

import com.stockMarket.controller.adapter.outbound.DBIncidentRepository;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.ActionName;
import com.stockMarket.controller.domain.Incident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMarketService {

    public final DBIncidentRepository dbIncidentRepository;

    public StockMarketService(DBIncidentRepository dbIncidentRepository) {
        this.dbIncidentRepository = dbIncidentRepository;
    }

    public void addNewIncident() {
        dbIncidentRepository.addNewIncidentToCheck(
                "test@wp.pl",
                new Incident("q221", 12, ActionName.SELL, 12));
    }


    public void removeIncidentById(Long id) {

    }

    public List<Incident> getAllIncidentByEmail(String email) {
        return List.of();
    }


}
