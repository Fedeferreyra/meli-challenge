package com.fferr10.melichallenge.solar.system.persistence.period;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class WeatherPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private WeatherResult.WeatherType weatherType;
    private Integer count;

    public WeatherPeriod() {
    }

    public WeatherPeriod(WeatherResult.WeatherType weatherType, Integer count) {
        this.weatherType = weatherType;
        this.count = count;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
