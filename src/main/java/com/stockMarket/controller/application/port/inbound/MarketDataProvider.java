package com.stockMarket.controller.application.port.inbound;

import java.util.List;
import java.util.Map;

public interface MarketDataProvider {
    Map<String, Float> getActionPriceForCompanyList(List<String> companyList);
}
