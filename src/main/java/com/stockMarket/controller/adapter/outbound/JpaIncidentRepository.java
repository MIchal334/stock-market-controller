package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIncidentRepository extends JpaRepository<IncidentEntity, Long> {
}