package com.lastminute.sales.taxes.quiz.math.rounding.rule.factory;

import com.lastminute.sales.taxes.quiz.math.rounding.rule.RoundingRuleType;
import com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.RoundingRuleStrategy;
import com.lastminute.sales.taxes.quiz.math.rounding.rule.strategy.impl.NearestFiveRoundingRuleStrategy;

import java.util.HashMap;
import java.util.Map;

public class RoundingRuleFactory {

    private static final Map<RoundingRuleType,RoundingRuleStrategy> rrStrategiesMap = new HashMap<>();

    static{
        rrStrategiesMap.put(RoundingRuleType.ROUNDING_NEAREST_5, new NearestFiveRoundingRuleStrategy());
    }

    public static RoundingRuleStrategy getStrategy(RoundingRuleType roundingRuleType){
        return rrStrategiesMap.get(roundingRuleType);
    }

}
