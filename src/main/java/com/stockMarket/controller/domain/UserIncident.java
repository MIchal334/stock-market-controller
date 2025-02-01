package com.stockMarket.controller.domain;


import java.util.List;


record UserIncident(String userEmail, List<Incident> incidentList) {
}
