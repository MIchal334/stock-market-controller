package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.ActionEntity;
import com.stockMarket.controller.adapter.dto.IncidentEntity;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.Incident;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBIncidentRepository implements IncidentRepository {
    private final JpaIncidentRepository jpaIncidentRepository;
    private final JpaActionRepository jpaActionRepository;

    public DBIncidentRepository(JpaIncidentRepository jpaIncidentRepository, JpaActionRepository jpaActionRepository) {
        this.jpaIncidentRepository = jpaIncidentRepository;
        this.jpaActionRepository = jpaActionRepository;
    }

    @Override
    public void addNewIncidentToCheck(String userEmail, Incident incident) {
        ActionEntity actionType = this.jpaActionRepository
                .findByActionName(incident.action().name())
                .orElseThrow(() -> new EntityNotFoundException("Action not found"));
        this.jpaIncidentRepository.save(IncidentEntity.of(userEmail, incident, actionType));
    }

    @Override
    public List<Incident> getAllIncident() {
        return List.of();
    }

    @Override
    public void deleteIncidentByID(Long id) {

    }
}
