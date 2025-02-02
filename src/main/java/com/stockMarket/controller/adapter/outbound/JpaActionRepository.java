package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.ActionEntity;
import com.stockMarket.controller.adapter.dto.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaActionRepository extends JpaRepository<ActionEntity, Long> {
    Optional<ActionEntity> findByActionName(String actionName);
}