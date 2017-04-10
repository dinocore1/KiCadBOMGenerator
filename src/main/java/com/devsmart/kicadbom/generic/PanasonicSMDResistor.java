package com.devsmart.kicadbom.generic;


import com.devsmart.kicadbom.Component;
import com.devsmart.kicadbom.GenericPartFactory;
import com.devsmart.kicadbom.LibSource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PanasonicSMDResistor implements GenericPartFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanasonicSMDResistor.class);

    private static final Pattern FOOTPRINT_SMD = Pattern.compile("Resistors_SMD:R_([0-9]+)");
    private static final ImmutableMap<String, String> SIZING_MAP_5P;
    private static final ImmutableMap<String, String> SIZING_MAP_1P;
    private static final Range<Double> VALUE_RANGE_5P;
    private static final Range<Double> VALUE_RANGE_1P;
    static {
        SIZING_MAP_5P = ImmutableMap.<String, String>builder()
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

        VALUE_RANGE_1P = Range.closed(10.0, 1000000.0);
    }

    @Override
    public String getMPN(Component c) {
        Matcher m;

        try {

        if(c.getMPN() == null &&
                LibSource.RESISTOR.equals(c.libSource) &&
                c.footprint != null &&
                c.value != null &&
                (m = FOOTPRINT_SMD.matcher(c.footprint)).find()) {

            final String footprintSize = m.group(1);
            String footprintCode;

            double value = StandardValues.parseValue(c.value);
            double decade = Math.floor(Math.log10(value));

            String tolerance = c.fields.get("tolerance");
            if(tolerance == null) {
                LOGGER.warn("no tolerance specified for: " + c.ref + " assuming 5%");
                tolerance = "5%";
            }

            if("1%".equals(tolerance)) {

                if((footprintCode = SIZING_MAP_1P.get(footprintSize)) == null){
                    throw new Exception("no matching footprint code: " + footprintSize);
                }

                if(!VALUE_RANGE_1P.contains(value)) {
                    throw new Exception("value: " + value + " not in range: " + VALUE_RANGE_1P);
                }

                double normalValue = value / Math.pow(10, decade);
                BigDecimal stdValue = StandardValues.E96.get(StandardValues.getIndexEIAStandard(normalValue, 96));
                String valueCode = stdValue.toPlainString();
                if(value > 100) {
                    valueCode = valueCode.replace(".", "");
                    if(valueCode.length() <= 2) {
                        valueCode += "0";
                    }

                } else if(value > 10) {
                    valueCode = valueCode.replace(".", "R");
                    if(valueCode.length() <= 2) {
                        valueCode += "0";
                    }

                } else {
                    valueCode = "10R0";
                }

                if(valueCode.length() < 4) {
                    valueCode += "" + (((int)decade) - 2);
                }

                return "ERJ" + footprintCode + "F" + valueCode + "V";


            } else if("5%".equals(tolerance)){

                if((footprintCode = SIZING_MAP_5P.get(footprintSize)) == null){
                    throw new Exception("no matching footprint code: " + footprintSize);
                }

                if(!VALUE_RANGE_5P.contains(value)) {
                    throw new Exception("value: " + value + " not in range: " + VALUE_RANGE_5P);
                }

                double normalValue = value / Math.pow(10, decade);
                BigDecimal stdValue = StandardValues.E24.get(StandardValues.getIndexEIAStandard(normalValue, 24));
                String valueCode = stdValue.toPlainString();

                if(value < 10) {
                    valueCode = "" + valueCode.charAt(0) + 'R' + valueCode.charAt(1);
                } else {
                    valueCode += "" + (((int)decade) - 1);
                }

                return "ERJ" + footprintCode + "YJ" + valueCode + "V";
            }

        }

        } catch (Exception e) {
            LOGGER.warn("", e);
        }

        return null;
    }
}
