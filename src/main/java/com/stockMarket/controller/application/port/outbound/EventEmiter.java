package com.stockMarket.controller.application.port.outbound;

import com.stockMarket.controller.domain.Incident;

public interface EventEmiter {

    void emit(Incident incident, String topic);

}
