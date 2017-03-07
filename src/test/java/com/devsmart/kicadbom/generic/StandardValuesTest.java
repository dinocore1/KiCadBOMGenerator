package com.devsmart.kicadbom.generic;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StandardValuesTest {


    @Test
    public void testStandardValues() {

        BigDecimal[] e4_std = StandardValues.createEIAStandardValues(4);
        assertEquals(4, e4_std.length);

        BigDecimal[] e6_std = StandardValues.createEIAStandardValues(6);
        assertEquals(6, e6_std.length);

        BigDecimal[] e12_std = StandardValues.createEIAStandardValues(12);
        assertEquals(12, e12_std.length);

    }
}