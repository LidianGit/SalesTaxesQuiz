package com.lastminute.taxesquiz.math.util;

import com.lastminute.taxesquiz.math.rounding.rule.RoundingRuleType;
import com.lastminute.taxesquiz.math.rounding.rule.factory.RoundingRuleFactory;

import java.math.BigDecimal;

public class MathUtil {

    private static final int ONE_HUNDRED = 100;

    public static BigDecimal percentOf(BigDecimal number, BigDecimal percentage, int scale){
        return number.multiply(percentage).divide(new BigDecimal(ONE_HUNDRED), scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal round(BigDecimal number, int scale, RoundingRuleType roundingRuleType){
        BigDecimal roundedNumber = RoundingRuleFactory.getStrategy(roundingRuleType).round(number, scale);
        return roundedNumber;
    }

}
