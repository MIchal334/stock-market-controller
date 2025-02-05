package com.stockMarket.controller.configuration;

import com.stockMarket.controller.adapter.inbound.FakeAPIMarketDataProvider;
import com.stockMarket.controller.adapter.outbound.DBIncidentRepository;
import com.stockMarket.controller.adapter.outbound.JpaActionRepository;
import com.stockMarket.controller.adapter.outbound.JpaIncidentRepository;
import com.stockMarket.controller.adapter.outbound.KafkaEventEmiter;
import com.stockMarket.controller.application.port.inbound.MarketDataProvider;
import com.stockMarket.controller.application.port.outbound.EventEmiter;
import com.stockMarket.controller.application.port.outbound.IncidentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class AppConfig {
    private final WebClient webClient;

    public AppConfig(WebClient webClient) {
        this.webClient = webClient;
    }

    @Bean
    IncidentRepository incidentRepository(JpaIncidentRepository jpaIncidentRepository, JpaActionRepository jpaActionRepository) {
        return new DBIncidentRepository(jpaIncidentRepository, jpaActionRepository);
    }

    @Bean
    MarketDataProvider marketDataProvider() {
        return new FakeAPIMarketDataProvider(webClient);
    }

    @Bean
    EventEmiter eventEmiter() {
        return new KafkaEventEmiter();
    }


}
