package com.fferr10.melichallenge.solar.system.model;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;

public class DailyWeatherResult {

    private Integer day;
    private WeatherResult weatherResult;

    public DailyWeatherResult(Integer day, WeatherResult weatherResult) {
        this.day = day;
        this.weatherResult = weatherResult;
    }

    public Integer getDay() {
        return day;
    }

    public WeatherResult getWeatherResult() {
        return weatherResult;
    }
}
