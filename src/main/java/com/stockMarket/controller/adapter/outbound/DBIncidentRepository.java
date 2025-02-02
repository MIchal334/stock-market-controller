package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.IncidentEntity;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import com.stockMarket.controller.domain.Incident;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface DBIncidentRepository extends JpaRepository<IncidentEntity, Long>, IncidentRepository {
    @Override
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO incident (companyName, priceThreshold, actionAmount, action_id, customer_email) " +
            "VALUES (:#{#incident.companyName}, :#{#incident.priceThreshold}, :#{#incident.actionAmount}, action_id_t, :email)",
    nativeQuery = true)
    void addNewIncidentToCheck(@Param("email") String userEmail,
                               @Param("action_id_t") int actionId,
                               @Param("incident") Incident incident);

//    @Override
//    List<Incident> getAllIncident();
//
//    @Override
//    void deleteIncidentByID(Long id);
}
