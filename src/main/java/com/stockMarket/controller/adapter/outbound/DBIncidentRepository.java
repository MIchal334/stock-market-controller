package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.IncidentEntity;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.Incident;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBIncidentRepository implements IncidentRepository {
    private final JpaIncidentRepository jpaIncidentRepository;

    public DBIncidentRepository(JpaIncidentRepository jpaIncidentRepository) {
        this.jpaIncidentRepository = jpaIncidentRepository;
    }

    @Override
    public void addNewIncidentToCheck(String userEmail, Incident incident) {
            this.jpaIncidentRepository.save(IncidentEntity.of(userEmail,incident));
    }

    @Override
    public List<Incident> getAllIncident() {
        return List.of();
    }

    @Override
    public void deleteIncidentByID(Long id) {

    }
}
