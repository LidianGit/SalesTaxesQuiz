package com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.impl;

import com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.RoundingRuleStrategy;

import java.math.BigDecimal;

public abstract class NearestRoundingRuleStrategy implements RoundingRuleStrategy {

    @Override
    public final BigDecimal round(BigDecimal number, int scale ) {
        return number.divide(new BigDecimal(getNearestNumber()), scale, BigDecimal.ROUND_CEILING ).multiply(new BigDecimal(getNearestNumber()));
    }

    protected abstract int getNearestNumber();
}
