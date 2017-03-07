package com.devsmart.kicadbom.generic;


import com.google.common.primitives.Floats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class StandardValues {

    static BigDecimal[] createEIAStandardValues(int e) {
        BigDecimal[] retval = new BigDecimal[e];
        final MathContext mc = new MathContext(e >= 48 ? 3 : 2, RoundingMode.HALF_DOWN);

        for(int i=0;i<e;i++) {
            System.out.println("int " + Math.pow(10, i/(double)e));
            retval[i] = new BigDecimal(Math.pow(10, i/(double)e), mc);
        }
        return retval;
    }

    static int getIndexEIAStandard(double value, int e) throws NumberFormatException {
        if(value < 1.0 || value > 10.0) {
            throw new NumberFormatException("value must be between 1 - 10");
        }
        return (int) (e * Math.log10(value));
    }


}
