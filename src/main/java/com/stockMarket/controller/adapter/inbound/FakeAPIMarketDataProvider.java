package com.stockMarket.controller.adapter.inbound;

import com.stockMarket.controller.application.port.inbound.MarketDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public class FakeAPIMarketDataProvider implements MarketDataProvider {
    @Value(value = "${market.dataEndpoint}")
    private String endpoint;

    @Autowired
    private final WebClient webClient;

    public FakeAPIMarketDataProvider(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Map<String, Float> getActionPriceForCompanyList(List<String> companyList) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Float>>() {
                })
                .block();
    }
}
