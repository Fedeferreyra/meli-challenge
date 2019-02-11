package com.fferr10.melichallenge.solar.system.persistence.period;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class LastPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private WeatherResult.WeatherType weatherType;
    private Integer MostRainyDay;
    private BigDecimal intensity;
    private Integer lastUpdated;

    public LastPeriod() {
    }

    public LastPeriod(WeatherResult.WeatherType weatherType, Integer lastUpdated) {
        this.weatherType = weatherType;
        this.lastUpdated = lastUpdated;
    }

    public LastPeriod(WeatherResult.WeatherType weatherType, Integer mostRainyDay, BigDecimal intensity, Integer lastUpdated) {
        this.weatherType = weatherType;
        MostRainyDay = mostRainyDay;
        this.intensity = intensity;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WeatherResult.WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherResult.WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public Integer getMostRainyDay() {
        return MostRainyDay;
    }

    public void setMostRainyDay(Integer mostRainyDay) {
        MostRainyDay = mostRainyDay;
    }

    public BigDecimal getIntensity() {
        return intensity;
    }

    public void setIntensity(BigDecimal intensity) {
        this.intensity = intensity;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
