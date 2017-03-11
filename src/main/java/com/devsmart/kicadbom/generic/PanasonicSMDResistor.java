package com.devsmart.kicadbom.generic;


import com.devsmart.kicadbom.Component;
import com.devsmart.kicadbom.GenericPartFactory;
import com.devsmart.kicadbom.LibSource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PanasonicSMDResistor implements GenericPartFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanasonicSMDResistor.class);

    private static final Pattern FOOTPRINT_SMD = Pattern.compile("Resistors_SMD:R_([0-9]+)");

    private static final ImmutableMap<String, String> SIZEING_MAP_5P;
    private static final ImmutableMap<String, String> SIZING_MAP_1P;
    private static final Range<Double> VALUE_RANGE_5P;
    static {
        SIZEING_MAP_5P = ImmutableMap.<String, String>builder()
                .put("0201", "1GN")
                .put("0402", "2GE")
                .put("0603", "3GE")
                .put("0805", "6GE")
                .put("1206", "8GE")
                .put("1210", "14")
                .put("1812", "12")
                .put("2010", "12Z")
                .build();

        VALUE_RANGE_5P = Range.closed(1.0, 10000000.0);

        SIZING_MAP_1P = ImmutableMap.<String, String>builder()
                .put("0201", "1GN")
                .put("0402", "2RK")
                .put("0603", "3EK")
                .put("0805", "6EN")
                .put("1206", "8EN")
                .build();
    }

    @Override
    public String getMPN(Component c) {
        Matcher m;
        String footprintCode;

        try {

        if(c.getMPN() == null &&
                LibSource.RESISTOR.equals(c.libSource) &&
                c.footprint != null &&
                c.value != null &&
                (m = FOOTPRINT_SMD.matcher(c.footprint)).find() &&
                (footprintCode = SIZEING_MAP_5P.get(m.group(1))) != null) {

            double value = StandardValues.parseValue(c.value);

            double decade = Math.floor(Math.log10(value));

            String tolerance = c.fields.get("tolerance");
            if(tolerance != null && "1%".equals(tolerance)) {
                double normalValue = value / Math.pow(10, decade);
                String stdValue = StandardValues.E96.get(StandardValues.getIndexEIAStandard(normalValue, 96));

                return "ERJ" + footprintCode + "F" + stdValue + (((int)decade) - 1) + "V";
            } else {
                //5%

                if(!VALUE_RANGE_5P.contains(value)) {
                    throw new Exception("value: " + value + " not in range: " + VALUE_RANGE_5P);
                }

                double normalValue = value / Math.pow(10, decade);
                String stdValue = StandardValues.E24.get(StandardValues.getIndexEIAStandard(normalValue, 24));

                return "ERJ" + footprintCode + "YJ" + stdValue + (((int)decade) - 1) + "V";
            }

        }

        } catch (Exception e) {
            LOGGER.warn("", e);
        }

        return null;
    }
}
