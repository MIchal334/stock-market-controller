package com.stockMarket.controller.domain;


import lombok.Getter;

import java.util.Map;
import java.util.HashMap;

@Getter
public enum CheckType {
    GREATER_THAN(">", (a, b) -> a > b),
    LESS_THAN("<", (a, b) -> a < b),
    GREATER_OR_EQUAL(">=", (a, b) -> a >= b),
    LESS_OR_EQUAL("<=", (a, b) -> a <= b);

    private final String symbol;
    private final CompareStrategy strategy;

    private static final Map<String, CheckType> SYMBOL_MAP = new HashMap<>();

    static {
        for (CheckType type : values()) {
            SYMBOL_MAP.put(type.symbol, type);
        }
    }

    CheckType(String symbol, CompareStrategy strategy) {
        this.symbol = symbol;
        this.strategy = strategy;
    }
    public boolean compare(float a, float b) {
        return strategy.compare(a, b);
    }

    public static CheckType fromSymbol(String symbol) {
        return SYMBOL_MAP.get(symbol);
    }

    @FunctionalInterface
    private interface CompareStrategy {
        boolean compare(float a, float b);
    }
}
