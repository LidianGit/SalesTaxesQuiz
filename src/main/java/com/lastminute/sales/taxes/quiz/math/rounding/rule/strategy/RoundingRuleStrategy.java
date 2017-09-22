package com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy;

import java.math.BigDecimal;

public interface RoundingRuleStrategy {

    BigDecimal round(BigDecimal number, int scale);

}
