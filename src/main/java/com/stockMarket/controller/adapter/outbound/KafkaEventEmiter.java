package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.application.port.outbound.EventEmiter;
import com.stockMarket.controller.domain.Incident;

class KafkaEventEmiter implements EventEmiter {
    @Override
    public void emit(Incident incident) {

    }
}
