package com.stockMarket.controller.domain;


import lombok.Getter;

@Getter
public enum CheckType {
    GREATER_THAN(">") {
        @Override
        public boolean compare(float a, float b) {
            return a > b;
        }
    },
    LESS_THAN("<") {
        @Override
        public boolean compare(float a, float b) {
            return a < b;
        }
    },

    GREATER_OR_EQUAL(">=") {
        @Override
        public boolean compare(float a, float b) {
            return a >= b;
        }
    },
    LESS_OR_EQUAL("<=") {
        @Override
        public boolean compare(float a, float b) {
            return a <= b;
        }
    };

    private final String symbol;

    CheckType(String symbol) {
        this.symbol = symbol;
    }

    public abstract boolean compare(float a, float b);


}
