package com.lastminute.sales.taxes.quiz.com.lastminute.sales.taxes.quiz.math.util;

import com.lastminute.sales.taxes.quiz.math.rounding.rule.RoundingRuleType;
import com.lastminute.sales.taxes.quiz.math.util.MathUtil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MathUtilTest {

    @Test
    public void percentOfTest(){
        assertEquals( "25.00", MathUtil.percentOf( new BigDecimal("100.00"), new BigDecimal("25.00"), 2 ).toString());
    }

    public void roundTest(){
        assertEquals( "2.40", MathUtil.round( new BigDecimal("2.38"), 2, RoundingRuleType.ROUNDING_NEAREST_5));
    }

}
