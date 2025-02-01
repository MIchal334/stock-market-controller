package com.stockMarket.controller.application.port.inbound;

import java.util.List;
import java.util.Map;

interface MarketDataProvider {
    Map<String, Float> getActionPriceForCompanyList(List<String> companyList);
}
