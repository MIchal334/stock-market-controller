package com.stockMarket.controller.application.port.inbound;

import com.stockMarket.controller.domain.Incident;

interface UserActionHandler {

    Incident addNewIncident();

    Long deleteIncidentById();

}
