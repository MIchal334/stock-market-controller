package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaIncidentRepository extends JpaRepository<IncidentEntity, Long> {
}