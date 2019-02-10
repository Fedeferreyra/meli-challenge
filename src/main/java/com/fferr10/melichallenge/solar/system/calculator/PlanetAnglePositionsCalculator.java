package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetAnglePositionsCalculator implements PositionsCalculator<Planet, Integer, Double> {

    @Override
    public Double calculate(Planet planet, Integer day) {
        double angle = (planet.getAngularSpeed() * day) % 360;
        if (angle < 0) {
            angle = angle + 360;
        }
        return Math.toRadians(angle);
    }
}
