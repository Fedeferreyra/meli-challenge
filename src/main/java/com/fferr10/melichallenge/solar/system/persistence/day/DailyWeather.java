package com.fferr10.melichallenge.solar.system.persistence.day;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;

import javax.persistence.*;

@Entity
public class DailyWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;
    @Enumerated(EnumType.STRING)
    private WeatherResult.WeatherType weatherType;

    public DailyWeather() {
    }

    public DailyWeather(Integer day, WeatherResult.WeatherType weatherType) {
        this.day = day;
        this.weatherType = weatherType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDay() {
        return day;
    }

    public WeatherResult.WeatherType getWeatherType() {
        return weatherType;
    }
}
