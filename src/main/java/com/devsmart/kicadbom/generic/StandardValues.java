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


    private static Pattern VALUE_REGEX = Pattern.compile("([0-9]+\\.?[0-9]*)([k|u|n|p|M|m])?");

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
            } else if("m".equals(scale)) {
                quanity *= 1e-3;
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

    public static ImmutableList<BigDecimal> E12;
    public static ImmutableList<BigDecimal> E24;
    public static ImmutableList<BigDecimal> E96;

    static {

        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);

        E12 = ImmutableList.<BigDecimal>builder()
                .add(new BigDecimal(10, mc))
                .add(new BigDecimal(12, mc))
                .add(new BigDecimal(15, mc))
                .add(new BigDecimal(18, mc))
                .add(new BigDecimal(22, mc))
                .add(new BigDecimal(27, mc))
                .add(new BigDecimal(33, mc))
                .add(new BigDecimal(39, mc))
                .add(new BigDecimal(47, mc))
                .add(new BigDecimal(56, mc))
                .add(new BigDecimal(68, mc))
                .add(new BigDecimal(82, mc))
                .build();

        E24 = ImmutableList.<BigDecimal>builder()
                .add(new BigDecimal(10, mc))
                .add(new BigDecimal(11, mc))
                .add(new BigDecimal(12, mc))
                .add(new BigDecimal(13, mc))
                .add(new BigDecimal(15, mc))
                .add(new BigDecimal(16, mc))
                .add(new BigDecimal(18, mc))
                .add(new BigDecimal(20, mc))
                .add(new BigDecimal(22, mc))
                .add(new BigDecimal(24, mc))
                .add(new BigDecimal(27, mc))
                .add(new BigDecimal(30, mc))
                .add(new BigDecimal(33, mc))
                .add(new BigDecimal(36, mc))
                .add(new BigDecimal(39, mc))
                .add(new BigDecimal(43, mc))
                .add(new BigDecimal(47, mc))
                .add(new BigDecimal(51, mc))
                .add(new BigDecimal(56, mc))
                .add(new BigDecimal(62, mc))
                .add(new BigDecimal(68, mc))
                .add(new BigDecimal(75, mc))
                .add(new BigDecimal(82, mc))
                .add(new BigDecimal(91, mc))
                .build();

        ImmutableList.Builder<BigDecimal> builder = ImmutableList.builder();
        mc = new MathContext(3, RoundingMode.HALF_DOWN);
        for(int i=0;i<96;i++){
            BigDecimal value = new BigDecimal(10*Math.pow(10, i/(double)96), mc);
            builder.add(value);
        }
        E96 = builder.build();

    }

    static int getIndexEIAStandard(double value, int e) throws NumberFormatException {
        if(value < 1.0 || value > 10.0) {
            throw new NumberFormatException("value must be between 1 - 10");
        }

        /**
         * Since the E3 to E24 series were defined and established even before IEC 63, some of the values
         * from 2.7 to 4.7 and 8.2 do not follow the rounding rules exactly, but weren't changed to avoid
         * confusion.
         * This should correct for that...
         */
        if(e >= 3 && e <= 24) {
            if (value >= 2.7 && value <= 4.7) {
                value -= 0.1;
            }
        }

        double d = e * Math.log10(value);
        int index = (int) Math.round(d);
        return index;
    }


}
