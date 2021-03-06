package com.lastminute.taxesquiz.math.rounding.rule.factory;

import com.lastminute.taxesquiz.math.rounding.rule.RoundingRuleType;
import com.lastminute.taxesquiz.math.rounding.rule.strategy.RoundingRuleStrategy;
import com.lastminute.taxesquiz.math.rounding.rule.strategy.impl.NearestFiveRoundingRuleStrategy;
import com.lastminute.taxesquiz.math.rounding.rule.strategy.impl.NearestTenRoundigRuleStrategy;

import java.util.HashMap;
import java.util.Map;

public class RoundingRuleFactory {

    private static final Map<RoundingRuleType,RoundingRuleStrategy> rrStrategiesMap = new HashMap<>();

    static{
        rrStrategiesMap.put(RoundingRuleType.ROUNDING_NEAREST_5, new NearestFiveRoundingRuleStrategy());
        rrStrategiesMap.put(RoundingRuleType.ROUNDING_NEAREST_10, new NearestTenRoundigRuleStrategy());
    }

    public static RoundingRuleStrategy getStrategy(RoundingRuleType roundingRuleType){
        return rrStrategiesMap.get(roundingRuleType);
    }

}
