package com.devsmart.kicadbom.generic;


import com.devsmart.kicadbom.Component;
import com.devsmart.kicadbom.LibSource;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanasonicSMDResistorTest {

    @Test
    public void test0603_1K_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "1k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ102V", mpn);

    }

    @Test
    public void test0603_10K_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "10k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ103V", mpn);

    }

    @Test
    public void test0603_700_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "700";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ681V", mpn);
    }
}
