package com.stockMarket.controller.application;

import com.stockMarket.controller.adapter.outbound.DBIncidentRepository;
import com.stockMarket.controller.application.port.inbound.MarketDataProvider;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.Incident;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


@Service
public class StockMarketService {
    public static final String DEFAULT_EMAIL = "test@wp.pl";
    private final IncidentRepository incidentRepository;
    private final MarketDataProvider marketDataProvider;

    public StockMarketService(IncidentRepository incidentRepository, MarketDataProvider marketDataProvider) {
        this.incidentRepository = incidentRepository;
        this.marketDataProvider = marketDataProvider;
    }


    public void addNewIncident(Incident incident) {
        incidentRepository.addNewIncidentToCheck(
                DEFAULT_EMAIL,
                incident);
    }


    public void removeIncidentById(Long id) {
        incidentRepository.deleteIncidentById(id);
    }

    public List<Incident> getAllIncidentByEmail(String email) {
        return incidentRepository.getAllIncidentByEmail(email);
    }

    public void checkIncidents() {
        List<Incident> incidentList = incidentRepository.getAllIncidentByEmail(DEFAULT_EMAIL);
        Map<String, Float> currentPriceState = marketDataProvider.getActionPriceForCompanyList(incidentList.stream()
                .map(Incident::companyName)
                .toList());
    }

    private List<Incident> findIncident(List<Incident> incidentsToCheck, Map<String, Float> currentPriceState) {
        return incidentsToCheck
                .stream()
                .filter(incident -> (currentPriceState.get(incident.companyName()) > incident.priceThreshold()) || currentPriceState.get(incident.companyName()) < incident.priceThreshold()).toList();
    }

}
