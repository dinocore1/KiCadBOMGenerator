package com.devsmart.kicadbom.generic;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StandardValuesTest {

    @Test
    public void testQuanityParser() {
        assertEquals(1000, StandardValues.parseValue("1k"), 0.0001);
        assertEquals(330, StandardValues.parseValue("330"), 0.0001);
        assertEquals(0.1e-6, StandardValues.parseValue("0.1uF"), 1e-10);

    }

    @Test
    public void testE12StandardValues() {
        assertEquals(0, StandardValues.getIndexEIAStandard(1.0, 12));
        assertEquals(0, StandardValues.getIndexEIAStandard(1.05, 12));

        assertEquals(1, StandardValues.getIndexEIAStandard(1.15, 12));

        assertEquals(1, StandardValues.getIndexEIAStandard(1.2, 12));
        assertEquals(2, StandardValues.getIndexEIAStandard(1.5, 12));
        assertEquals(3, StandardValues.getIndexEIAStandard(1.8, 12));
        assertEquals(4, StandardValues.getIndexEIAStandard(2.2, 12));
        assertEquals(5, StandardValues.getIndexEIAStandard(2.7, 12));
        assertEquals(6, StandardValues.getIndexEIAStandard(3.3, 12));
        assertEquals(7, StandardValues.getIndexEIAStandard(3.9, 12));
        assertEquals(7, StandardValues.getIndexEIAStandard(4.0, 12));

        assertEquals(8, StandardValues.getIndexEIAStandard(4.7, 12));
        assertEquals(9, StandardValues.getIndexEIAStandard(5.6, 12));
        assertEquals(10, StandardValues.getIndexEIAStandard(6.8, 12));
        assertEquals(11, StandardValues.getIndexEIAStandard(8.2, 12));

        assertEquals(11, StandardValues.getIndexEIAStandard(9.0, 12));
    }


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
