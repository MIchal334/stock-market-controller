package com.stockMarket.controller.configuration;

import com.stockMarket.controller.adapter.outbound.DBIncidentRepository;
import com.stockMarket.controller.adapter.outbound.JpaActionRepository;
import com.stockMarket.controller.adapter.outbound.JpaIncidentRepository;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    IncidentRepository incidentRepository(JpaIncidentRepository jpaIncidentRepository, JpaActionRepository jpaActionRepository) {
        return new DBIncidentRepository(jpaIncidentRepository, jpaActionRepository);
    }

}
