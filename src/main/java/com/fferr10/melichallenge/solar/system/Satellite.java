package com.fferr10.melichallenge.solar.system;

import com.fferr10.melichallenge.solar.system.calculator.SolarSystemPositionsPositionsCalculator;
import com.fferr10.melichallenge.solar.system.interpreter.PositionsInterpreter;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.model.SolarSystem;
import com.fferr10.melichallenge.solar.system.model.SolarSystemPositions;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.observer.Observable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Satellite extends Observable<DailyWeatherResult> {

    private final SolarSystem solarSystem;
    private final SolarSystemPositionsPositionsCalculator solarSystemPositionsCalculator;
    private final PositionsInterpreter positionsInterpreter;

    public Satellite(SolarSystem solarSystem,
                     SolarSystemPositionsPositionsCalculator solarSystemPositionsCalculator, PositionsInterpreter positionsInterpreter) {
        super();
        this.solarSystem = solarSystem;
        this.solarSystemPositionsCalculator = solarSystemPositionsCalculator;
        this.positionsInterpreter = positionsInterpreter;
    }

    public void predictWeather() {
        Integer day = 0;
        while (day <= getSlowestPlanetTenYears()) {
            SolarSystemPositions solarSystemPositions = solarSystemPositionsCalculator.calculate(solarSystem, day);
            WeatherResult weatherResult = positionsInterpreter.apply(solarSystemPositions);
            this.notifyObservers(new DailyWeatherResult(day, weatherResult));
            day++;
        }
    }

    private int getSlowestPlanetTenYears() {
        return (360 / solarSystem.getSlowestPlanet().getAngularSpeed()) * 10;
    }
}
