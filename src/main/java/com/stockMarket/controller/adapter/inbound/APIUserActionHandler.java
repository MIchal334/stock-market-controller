package com.stockMarket.controller.adapter.inbound;

import com.stockMarket.controller.application.StockMarketService;
import com.stockMarket.controller.domain.Incident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
class APIUserActionHandler {

    private final StockMarketService stockMarketService;

    APIUserActionHandler(StockMarketService stockMarketService) {
        this.stockMarketService = stockMarketService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Incident>> getAllIncidentByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.stockMarketService.getAllIncidentByEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> addNewIncident(@RequestBody Incident incident) {
        this.stockMarketService.addNewIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidentById(@PathVariable Long id) {
        this.stockMarketService.removeIncidentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
