package com.fferr10.melichallenge.solar.system.persistence.period;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class RainyPeriod extends WeatherPeriod{


    @OneToOne(cascade = CascadeType.ALL)
    private MostRainyDay mostRainyDay;


    public RainyPeriod() { }

    public RainyPeriod(Integer count, MostRainyDay mostRainyDay) {
        super(WeatherResult.WeatherType.RAINY, count);
        this.mostRainyDay = mostRainyDay;
    }

    public MostRainyDay getMostRainyDay() {
        return mostRainyDay;
    }

    public void setMostRainyDay(MostRainyDay mostRainyDay) {
        this.mostRainyDay = mostRainyDay;
    }
}
