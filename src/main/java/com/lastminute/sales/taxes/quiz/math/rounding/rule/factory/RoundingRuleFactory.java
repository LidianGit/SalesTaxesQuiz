package com.lastminute.sales.taxes.quiz.calculation.rounding.rule.factory;

import com.lastminute.sales.taxes.quiz.calculation.rounding.rule.RoundingRuleType;
import com.lastminute.sales.taxes.quiz.calculation.rounding.rule.strategy.RoundingRuleStrategy;
import com.lastminute.sales.taxes.quiz.calculation.rounding.rule.strategy.impl.Nearest5RoundingRuleStrategy;

import java.util.HashMap;
import java.util.Map;

public class RoundingRuleFactory {

    private static final Map<RoundingRuleType,RoundingRuleStrategy> rrStrategiesMap = new HashMap<>();

    static{
        rrStrategiesMap.put(RoundingRuleType.NEAREST_5, new Nearest5RoundingRuleStrategy());
    }

    public static RoundingRuleStrategy getStrategy(String roundingRule){
        return rrStrategiesMap.get(roundingRule);
    }

}
