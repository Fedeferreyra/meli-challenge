package com.fferr10.melichallenge.solar.system;

import com.fferr10.melichallenge.solar.system.calculator.SolarSystemPositionsCalculator;
import com.fferr10.melichallenge.solar.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.interpreter.PositionsInterpreter;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.observer.Observer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SatelliteTest extends TestFixture {

    public static final int FARENGI_TEN_YEARS = 360 * 10;
    private Satellite satellite;
    @Mock
    private SolarSystemPositionsCalculator solarSystemPositionsCalculator;
    @Mock
    private PositionsInterpreter positionsInterpreter;
    @Mock
    private Observer<DailyWeatherResult> observer;

    @Before
    public void setUp(){
        Mockito.when(solarSystemPositionsCalculator.calculate(Mockito.any(), Mockito.anyInt()))
                .thenReturn(solarSystemPositions);
        Mockito.when(positionsInterpreter.apply(solarSystemPositions)).thenReturn(randomWeatherResult());
        Mockito.doNothing().when(observer).update(Mockito.any());
        satellite = new Satellite(solarSystemPositionsCalculator, positionsInterpreter);
        satellite.subscribe(observer);
    }

    @Test
    public void verifySolarSystemPositionsCalculatorIsCalledOncePerDay(){
        satellite.predictWeather();

        Mockito.verify(solarSystemPositionsCalculator, Mockito.times(FARENGI_TEN_YEARS))
                .calculate(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void verifyPositionsInterpreterIsCalledOncePerDay(){
        satellite.predictWeather();

        Mockito.verify(positionsInterpreter, Mockito.times(FARENGI_TEN_YEARS)).apply(solarSystemPositions);
    }

    @Test
    public void verifyObserverIsCalledOncePerDay(){
        satellite.predictWeather();
        Mockito.verify(observer, Mockito.times(FARENGI_TEN_YEARS)).update(Mockito.any());
    }
}