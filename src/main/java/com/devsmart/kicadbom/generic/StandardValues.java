package com.devsmart.kicadbom.generic;


import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Floats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StandardValues {


    private static Pattern VALUE_REGEX = Pattern.compile("([0-9]+\\.?[0-9]*)([k|u|n|p|M])?");

    public static double parseValue(String valueStr) throws NumberFormatException {
        Matcher m = VALUE_REGEX.matcher(valueStr);
        if(!m.find()) {
            throw new NumberFormatException("could not parse: " + valueStr);
        }

        double quanity = Double.valueOf(m.group(1));
        String scale;
        if(m.groupCount() == 2 && (scale = m.group(2)) != null) {
            if("k".equals(scale)){
                quanity *= 1e3;
            } else if("u".equals(scale)) {
                quanity *= 1e-6;
            } else if("n".equals(scale)) {
                quanity *= 1e-9;
            } else if("p".equals(scale)) {
                quanity *= 1e-12;
            } else if("M".equals(scale)) {
                quanity *= 1e6;
            }
        }

        return quanity;
    }

    static BigDecimal[] createEIAStandardValues(int e) {
        BigDecimal[] retval = new BigDecimal[e];
        final MathContext mc = new MathContext(e >= 48 ? 3 : 2, RoundingMode.HALF_DOWN);

        for(int i=0;i<e;i++) {
            System.out.println("int " + Math.pow(10, i/(double)e));
            retval[i] = new BigDecimal(Math.pow(10, i/(double)e), mc);
        }
        return retval;
    }

    public static ImmutableList<String> E12;
    public static ImmutableList<String> E24;
    public static ImmutableList<String> E96;

    static {
        E12 = ImmutableList.<String>builder()
                .add("10")
                .add("12")
                .add("15")
                .add("18")
                .add("22")
                .add("27")
                .add("33")
                .add("39")
                .add("47")
                .add("56")
                .add("68")
                .add("82")
                .build();

        E24 = ImmutableList.<String>builder()
                .add("10")
                .add("11")
                .add("12")
                .add("13")
                .add("15")
                .add("16")
                .add("18")
                .add("20")
                .add("22")
                .add("24")
                .add("27")
                .add("30")
                .add("33")
                .add("36")
                .add("39")
                .add("43")
                .add("47")
                .add("51")
                .add("56")
                .add("62")
                .add("68")
                .add("75")
                .add("82")
                .add("91")
                .build();

        ImmutableList.Builder<String> builder = ImmutableList.builder();
        final MathContext mc = new MathContext(3, RoundingMode.HALF_DOWN);
        for(int i=0;i<96;i++){
            BigDecimal value = new BigDecimal(10*Math.pow(10, i/(double)96), mc);
            builder.add(value.toPlainString());
        }
        E96 = builder.build();

    }

    static int getIndexEIAStandard(double value, int e) throws NumberFormatException {
        if(value < 1.0 || value > 10.0) {
            throw new NumberFormatException("value must be between 1 - 10");
        }
        return (int) (e * Math.log10(value));
    }


}
