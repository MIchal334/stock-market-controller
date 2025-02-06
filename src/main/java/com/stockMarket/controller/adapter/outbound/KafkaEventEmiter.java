package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.application.port.outbound.EventEmiter;
import com.stockMarket.controller.domain.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventEmiter implements EventEmiter {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void emit(Incident incident, String topic) {
        this.kafkaTemplate.send(topic, incident.toJson());
    }
}
