package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Point;
import com.fferr10.melichallenge.solar.system.model.SolarSystem;
import com.fferr10.melichallenge.solar.system.model.SolarSystemPositions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolarSystemPositionsPositionsCalculator implements PositionsCalculator<SolarSystem, Integer, SolarSystemPositions> {

    private final PlanetPositionPositionsCalculator planetPositionCalculator;

    public SolarSystemPositionsPositionsCalculator(PlanetPositionPositionsCalculator planetPositionCalculator) {
        this.planetPositionCalculator = planetPositionCalculator;
    }

    @Override
    public SolarSystemPositions calculate(SolarSystem solarSystem, Integer day) {
        List<Point> planetPoints = solarSystem.getPlanets()
                .parallelStream()
                .map(planet -> planetPositionCalculator.calculate(planet, day))
                .collect(Collectors.toList());
        return new SolarSystemPositions(planetPoints, solarSystem.getSunPoint());
    }
}
