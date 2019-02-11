package com.fferr10.melichallenge.solar.system;

import com.fferr10.melichallenge.solar.system.calculator.SolarSystemPositionsCalculator;
import com.fferr10.melichallenge.solar.system.interpreter.PositionsInterpreter;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.model.SolarSystem;
import com.fferr10.melichallenge.solar.system.model.SolarSystemPositions;
import com.fferr10.melichallenge.solar.system.observer.Observable;
import org.springframework.stereotype.Component;

@Component
public class Satellite extends Observable<DailyWeatherResult> {

    private final SolarSystem solarSystem;
    private final SolarSystemPositionsCalculator solarSystemPositionsCalculator;
    private final PositionsInterpreter positionsInterpreter;

    public Satellite(SolarSystemPositionsCalculator solarSystemPositionsCalculator,
                     PositionsInterpreter positionsInterpreter) {
        super();
        this.solarSystem = new SolarSystem();
        this.solarSystemPositionsCalculator = solarSystemPositionsCalculator;
        this.positionsInterpreter = positionsInterpreter;
    }

    public void predictWeather() {
        Integer day = 1;
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
