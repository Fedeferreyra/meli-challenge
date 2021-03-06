package com.fferr10.melichallenge.solar.fixture;

import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.*;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriod;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.format;
import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

public class TestFixture {
    protected Planet zeroAngularSpeedPlanet = new Planet("zeroAngularSpeedPlanet", 0, 100);
    protected Planet allPositivePlanet = new Planet("allPositivePlanet", 20, 200);
    protected Planet allNegativePlanet = new Planet("allPositivePlanet", -20, -200);
    protected Planet zeroDistanceToSunPlanet = new Planet("zeroDistanceToSunPlanet", 20, 0);
    protected Planet allZeroPlanet = new Planet("test", 0, 0);


    protected Point origin = new Point(format(BigDecimal.ZERO), format(BigDecimal.ZERO));

    protected Point getPointWithX(BigDecimal x) {
        return new Point(x, format(BigDecimal.ZERO));
    }

    protected Point getPointWithY(BigDecimal y) {
        return new Point(format(BigDecimal.ZERO), y);

    }

    protected Point randomPoint() {
        return new Point(toBigDecimal(new Random().nextDouble()), toBigDecimal(new Random().nextDouble()));
    }

    private List<Point> planetPoints = Lists.newArrayList(randomPoint(), randomPoint(), randomPoint());
    protected SolarSystemPositions solarSystemPositions = new SolarSystemPositions(planetPoints, origin);

    protected WeatherResult randomWeatherResult() {
        List<WeatherResult.WeatherType> weatherTypes = Arrays.asList(WeatherResult.WeatherType.values());

        return new WeatherResult(weatherTypes.get(new Random().nextInt(4)));
    }

    protected DailyWeatherResult droughtDailyWeatherResult = new DailyWeatherResult(1,
            new WeatherResult(WeatherResult.WeatherType.DROUGHT));

    protected DailyWeatherResult rainyDailyWeatherResult = new DailyWeatherResult(1,
            new RainyWeatherResult(toBigDecimal(9)));

    protected LastPeriod droughtLastPeriod = new LastPeriod(WeatherResult.WeatherType.DROUGHT,
            droughtDailyWeatherResult.getDay());

    protected LastPeriod rainyLastPeriod = new LastPeriod(WeatherResult.WeatherType.RAINY, rainyDailyWeatherResult.getDay(),
            toBigDecimal(2), rainyDailyWeatherResult.getDay());
}
