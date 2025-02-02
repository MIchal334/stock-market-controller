package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.ActionEntity;
import com.stockMarket.controller.adapter.dto.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaActionRepository extends JpaRepository<ActionEntity, Long> {
    Optional<ActionEntity> findByActionName(String actionName);
}