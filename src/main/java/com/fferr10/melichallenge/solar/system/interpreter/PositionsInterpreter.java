package com.fferr10.melichallenge.solar.system.interpreter;

import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.*;
import com.fferr10.melichallenge.solar.system.model.shape.Shape;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PositionsInterpreter implements Function<SolarSystemPositions, WeatherResult> {

    @Override
    public WeatherResult apply(SolarSystemPositions solarSystemPositions) {
        Shape shape = Shape.build(solarSystemPositions.getPlanetPoints());
        Point sunPoint = solarSystemPositions.getSunPoint();

        if (shape.areVertexesCollinear()) {
            if (shape.areVertexesCollinearWithPoint(sunPoint)) {
                return new WeatherResult(WeatherResult.WeatherType.DROUGHT);
            }
            return new WeatherResult(WeatherResult.WeatherType.OPTIMAL);
        } else if (shape.isPointInside(sunPoint)) {
            return new RainyWeatherResult(WeatherResult.WeatherType.RAINY, shape.getPerimeter());
        } else {
            return new WeatherResult(WeatherResult.WeatherType.TRANSITION);
        }
    }
}
