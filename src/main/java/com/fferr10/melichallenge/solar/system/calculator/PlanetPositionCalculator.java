package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Planet;
import com.fferr10.melichallenge.solar.system.model.Point;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

@Component
public class PlanetPositionCalculator implements PositionsCalculator<Planet, Integer, Point> {

    private final PlanetAnglePositionsCalculator planetAngleCalculator;

    public PlanetPositionCalculator(PlanetAnglePositionsCalculator planetAngleCalculator) {
        this.planetAngleCalculator = planetAngleCalculator;
    }

    @Override
    public Point calculate(Planet planet, Integer day) {
        BigDecimal angle = planetAngleCalculator.calculate(planet, day);

        BigDecimal x = toBigDecimal(planet.getDistanceToSun() * Math.cos(angle.doubleValue()));
        BigDecimal y = toBigDecimal(planet.getDistanceToSun() * Math.sin(angle.doubleValue()));

        return new Point(x, y);
    }
}
