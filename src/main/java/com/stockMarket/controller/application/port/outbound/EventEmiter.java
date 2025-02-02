package com.stockMarket.controller.application.port.outbound;

import com.stockMarket.controller.domain.Incident;

interface EventEmiter {

    void emit(Incident incident);

}
