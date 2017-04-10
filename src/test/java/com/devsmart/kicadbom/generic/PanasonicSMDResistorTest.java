package com.devsmart.kicadbom.generic;


import com.devsmart.kicadbom.Component;
import com.devsmart.kicadbom.LibSource;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanasonicSMDResistorTest {

    @Test
    public void test0603_4R7_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "4.7";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ4R7V", mpn);
    }

    @Test
    public void test0603_10_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "10";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ100V", mpn);
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
    public void test0603_40R2K_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "40.2k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ393V", mpn);

    }


    @Test
    public void test0603_100K_5() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "100k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3GEYJ104V", mpn);
    }


    @Test
    public void test0603_1_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "1";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        //min range is 10
        assertNull(mpn);

    }

    @Test
    public void test0603_10_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "10";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF10R0V", mpn);

    }

    @Test
    public void test0603_41R2_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "41.2";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF41R2V", mpn);

    }

    @Test
    public void test0603_100_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "100";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF1000V", mpn);

    }

    @Test
    public void test0603_1000_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "1k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF1001V", mpn);

    }

    @Test
    public void test0603_16R2K_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "16.2k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF1622V", mpn);

    }


    @Test
    public void test0603_35R7K_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "35.7k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF3572V", mpn);

    }

    @Test
    public void test0603_100K_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "100k";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF1003V", mpn);

    }

    @Test
    public void test0603_1M_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "1M";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        String mpn = r.getMPN(c);
        assertNotNull(mpn);
        assertEquals("ERJ3EKF1004V", mpn);

    }

    @Test
    public void test0603_1R2M_1() {
        PanasonicSMDResistor r = new PanasonicSMDResistor();

        Component c = new Component("R1");
        c.value = "1.2M";
        c.footprint = "Resistors_SMD:R_0603";
        c.libSource = LibSource.RESISTOR;
        c.fields.put("tolerance", "1%");

        //max range is 1M
        String mpn = r.getMPN(c);
        assertNull(mpn);
    }

}
