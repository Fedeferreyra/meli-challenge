package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Planet;
import com.fferr10.melichallenge.solar.system.model.Point;
import org.springframework.stereotype.Component;

@Component
public class PlanetPositionPositionsCalculator implements PositionsCalculator<Planet, Integer, Point> {

    private final PlanetAnglePositionsCalculator planetAngleCalculator;

    public PlanetPositionPositionsCalculator(PlanetAnglePositionsCalculator planetAngleCalculator) {
        this.planetAngleCalculator = planetAngleCalculator;
    }

    @Override
    public Point calculate(Planet planet, Integer day) {
        Double angle = planetAngleCalculator.calculate(planet, day);

        Double x = (planet.getDistanceToSun() * Math.cos(angle));
        Double y = (planet.getDistanceToSun() * Math.sin(angle));

        return new Point(x, y);
    }
}
