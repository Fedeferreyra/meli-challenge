package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Planet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

@Component
public class PlanetAnglePositionsCalculator implements PositionsCalculator<Planet, Integer, BigDecimal> {

    @Override
    public BigDecimal calculate(Planet planet, Integer day) {
        double angle = (planet.getAngularSpeed() * day) % 360;

        return toBigDecimal(Math.toRadians(angle));
    }
}
