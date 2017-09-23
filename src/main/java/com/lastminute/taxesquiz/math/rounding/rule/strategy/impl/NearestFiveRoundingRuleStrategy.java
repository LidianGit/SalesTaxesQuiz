package com.lastminute.taxesquiz.math.rounding.rule.strategy.impl;

public class NearestFiveRoundingRuleStrategy extends NearestRoundingRuleStrategy {

    private static final int NEAREST_NUMBER = 5;

    @Override
    protected int getNearestNumber() {
        return NEAREST_NUMBER;
    }
}
