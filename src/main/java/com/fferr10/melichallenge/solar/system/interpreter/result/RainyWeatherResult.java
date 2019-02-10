package com.fferr10.melichallenge.solar.system.interpreter.result;

public class RainyWeatherResult extends WeatherResult {

    private Double intensity;

    public RainyWeatherResult(WeatherType weatherType, Double intensity) {
        super(weatherType);
        this.intensity = intensity;
    }

    public Double getIntensity() {
        return intensity;
    }
}
