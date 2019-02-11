package com.fferr10.melichallenge.solar.system.interpreter.result;

import java.math.BigDecimal;

public class RainyWeatherResult extends WeatherResult {

    private BigDecimal intensity;

    public RainyWeatherResult(BigDecimal intensity) {
        super(WeatherType.RAINY);
        this.intensity = intensity;
    }

    public BigDecimal getIntensity() {
        return intensity;
    }
}
