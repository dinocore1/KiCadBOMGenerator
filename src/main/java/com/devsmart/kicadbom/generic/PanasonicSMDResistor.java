package com.devsmart.kicadbom.generic;


import com.devsmart.kicadbom.Component;
import com.devsmart.kicadbom.GenericPartFactory;
import com.devsmart.kicadbom.LibSource;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PanasonicSMDResistor implements GenericPartFactory {

    private static final Pattern FOOTPRINT_SMD = Pattern.compile("Resistors_SMD:R_([0-9]+)");

    private static final TreeMap<String, String> SIZEING_MAP = new TreeMap<String, String>();
    static {
        SIZEING_MAP.put("0201", "1GN");
        SIZEING_MAP.put("0402", "2GE");
        SIZEING_MAP.put("0603", "3GE");
        SIZEING_MAP.put("0805", "6GE");
        SIZEING_MAP.put("1206", "8GE");
    }

    @Override
    public String getMPN(Component c) {
        Matcher m;
        String footprintCode;

        if(c.getMPN() == null &&
                LibSource.RESISTOR.equals(c.libSource) &&
                c.footprint != null &&
                c.value != null &&
                (m = FOOTPRINT_SMD.matcher(c.footprint)).find() &&
                (footprintCode = SIZEING_MAP.get(m.group(1))) != null) {





        }

        return null;
    }
}
