package com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.impl;

public class NearestTenRoundigRuleStrategy extends NearestRoundingRuleStrategy {

    private static final int NEAREST_NUMBER = 10;

    @Override
    protected int getNearestNumber() {
        return NEAREST_NUMBER;
    }
}
