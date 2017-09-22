package com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.impl;

public class Nearest5RoundingRuleStrategy extends NearestRoundingRuleStrategy {

    private static final int NEAREST_NUMBER = 5;
//
//    @Override
//    public BigDecimal round( BigDecimal number, int scale ) {
//        return number.divide(new BigDecimal(NEAREST), scale, BigDecimal.ROUND_CEILING ).multiply(new BigDecimal(NEAREST));
//    }
//
    @Override
    protected int getNearestNumber() {
        return NEAREST_NUMBER;
    }
}
