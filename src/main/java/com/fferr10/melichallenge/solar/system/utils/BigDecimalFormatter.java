package com.fferr10.melichallenge.solar.system.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalFormatter {

    public static BigDecimal format(BigDecimal bigDecimal){
        return bigDecimal.setScale(8, RoundingMode.HALF_UP);
    }

    public static BigDecimal toBigDecimal(Double value){
        return format(BigDecimal.valueOf(value));
    }

    public static BigDecimal toBigDecimal(Integer value){
        return format(BigDecimal.valueOf(value));
    }
}
