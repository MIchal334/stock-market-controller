package com.stockMarket.controller.application;

import com.stockMarket.controller.application.port.inbound.MarketDataProvider;
import com.stockMarket.controller.application.port.outbound.EventEmiter;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.Incident;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class StockMarketService {
    public static final String DEFAULT_EMAIL = "test@wp.pl";
    public static final String DEFAULT_TOPIC_NAME = "incident_topic";
    private final IncidentRepository incidentRepository;
    private final MarketDataProvider marketDataProvider;
    private final EventEmiter eventEmiter;

    public StockMarketService(IncidentRepository incidentRepository, MarketDataProvider marketDataProvider, EventEmiter eventEmiter) {
        this.incidentRepository = incidentRepository;
        this.marketDataProvider = marketDataProvider;
        this.eventEmiter = eventEmiter;
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

    @Scheduled(fixedRate = 5 * 1000)
    public void checkIncidents() {
        System.out.println("CHECK");
        List<Incident> incidentList = incidentRepository.getAllIncidentByEmail(DEFAULT_EMAIL);
        Map<String, Float> currentPriceState = marketDataProvider.getActionPriceForCompanyList(incidentList.stream()
                .map(Incident::companyName)
                .toList());
        List<Incident> incidentToPropagate = findIncidentToPropagate(incidentList, currentPriceState);
        propagateIncidentList(incidentToPropagate);
        removeIncidentFromList(incidentToPropagate);
    }

    private void propagateIncidentList(List<Incident> incidentToPropagate) {
        incidentToPropagate.forEach(incident -> eventEmiter.emit(incident, DEFAULT_TOPIC_NAME));
    }

    private void removeIncidentFromList(List<Incident> incidentToRemove) {
        incidentToRemove.forEach(incident -> removeIncidentById(incident.internalId()));
    }

    private List<Incident> findIncidentToPropagate(List<Incident> incidentsToCheck, Map<String, Float> currentPriceState) {
        return incidentsToCheck.stream()
                .filter(incident -> {
                    Float currentPrice = currentPriceState.get(incident.companyName());
                    return currentPrice != null && incident.checkType().compare(currentPrice, incident.priceThreshold());
                })
                .toList();
    }

}
