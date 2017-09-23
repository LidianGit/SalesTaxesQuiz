package com.lastminute.taxesquiz.math.rounding.rule.strategy.impl;

import com.lastminute.taxesquiz.math.rounding.rule.strategy.RoundingRuleStrategy;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class NearestTenRoundigRuleStrategyTest {

    @Test
    public void testRound(){
        RoundingRuleStrategy roundingRuleStrategy = new NearestTenRoundigRuleStrategy();
        assertEquals( "0.00" , roundingRuleStrategy.round(new BigDecimal("0.00"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.01"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.02"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.03"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.04"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.05"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.06"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.07"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.08"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.09"), 2).toString() );
        assertEquals( "0.10" , roundingRuleStrategy.round(new BigDecimal("0.10"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.11"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.12"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.13"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.14"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.15"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.16"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.17"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.18"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.19"), 2).toString() );
        assertEquals( "0.20" , roundingRuleStrategy.round(new BigDecimal("0.20"), 2).toString() );
    }

}
