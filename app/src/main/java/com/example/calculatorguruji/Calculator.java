package com.example.calculatorguruji;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Calculator {
    private final String DEFAULT_VALUE;;

    public Calculator() {
        DEFAULT_VALUE = "ॐ";
    }

    public String makeNumberNegative(String str) {
        if (str != null) {
            try {
                double d = (Double.parseDouble(str)) * -1;
                return convertValue(d);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return DEFAULT_VALUE;
    }

    public String checkNumsBeforeDot(String s) {
        if (s.startsWith("0")) {
            return trimZeroBeforeDot(s);
        }
        if (s.equals(DEFAULT_VALUE) || s == null) {
            return "0.";
        }
        return s + ".";
    }

    private String trimZeroBeforeDot(String s) {
            int firstNonZeroAtDigit = 0;
            char[] charArr = s.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                if (!String.valueOf(charArr[i]).equalsIgnoreCase("0")) {
                    firstNonZeroAtDigit = i;
                    break;
                }
            }
            if(firstNonZeroAtDigit == 0) {
                return "0.";
            }

            char [] newArray = Arrays.copyOfRange(charArr, firstNonZeroAtDigit, charArr.length);
            return new String(newArray) + ".";
    }

    public String calcPercent(String s1, String s2, Symbols smb) {
        if (s1 != null && s2 != null && smb != null) {
            try {
                double val1 = Double.parseDouble(s1);
                double val2 = Double.parseDouble(s2);
                return convertValue(val1 * val2 / 100);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return DEFAULT_VALUE;
    }

    public String getResult(String s1, String s2, Symbols smb) {
        if (s1 != null && s2 != null && smb != null) {
            return calcResult(s1, s2, smb);
        }
        return DEFAULT_VALUE;
    }

    private String calcResult(String s1, String s2, Symbols smb) {

        try {

            double val1 = Double.parseDouble(s1);
            double val2 = Double.parseDouble(s2);

            switch (smb) {
                case sum:
                    return String.valueOf(convertValue(val1 + val2));
                case sub:
                    return String.valueOf(convertValue(val1 - val2));
                case mul:
                    return String.valueOf(convertValue(val1 * val2));
                case div:
                    if (val2 != 0) {
                        return String.valueOf(convertValue(val1 / val2));
                    }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return DEFAULT_VALUE;
    }

    public String convertValue(Double d) {
        if (d == d.intValue()) {
            return String.valueOf(d.intValue());
        } else {
            d = new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
            return d.toString();
        }
    }

}
