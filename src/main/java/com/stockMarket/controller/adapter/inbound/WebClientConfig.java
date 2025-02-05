package com.stockMarket.controller.adapter.inbound;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value(value = "${market.address}")
    private String marketAddress;

    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(marketAddress)
                .build();
    }

}
