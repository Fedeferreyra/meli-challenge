package com.fferr10.melichallenge.solar.system.interpreter;

import com.fferr10.melichallenge.solar.system.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.shape.Shape;
import com.fferr10.melichallenge.solar.system.model.shape.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

@PrepareForTest(Shape.class)
@RunWith(PowerMockRunner.class)
public class PositionsInterpreterTest extends TestFixture {

    @Mock
    private Triangle triangle;
    private PositionsInterpreter positionsInterpreter;

    @Before
    public void setUp(){
        PowerMockito.mockStatic(Shape.class);
        Mockito.when(Shape.build(Mockito.anyList())).thenReturn(triangle);
        positionsInterpreter = new PositionsInterpreter();
    }

    @Test
    public void shouldReturnOptimalWeatherWhenTriangleVertexesAreCollinearButNotWithSun(){
        Mockito.when(triangle.areVertexesCollinear()).thenReturn(true);
        Mockito.when(triangle.areVertexesCollinearWithPoint(Mockito.any())).thenReturn(false);

        WeatherResult result = positionsInterpreter.apply(solarSystemPositions);

        Assert.assertEquals(WeatherResult.WeatherType.OPTIMAL, result.getWeatherType());
    }

    @Test
    public void shouldReturnDroughtWeatherWhenTriangleVertexesAreCollineaWithSun(){
        Mockito.when(triangle.areVertexesCollinear()).thenReturn(true);
        Mockito.when(triangle.areVertexesCollinearWithPoint(Mockito.any())).thenReturn(true);

        WeatherResult result = positionsInterpreter.apply(solarSystemPositions);

        Assert.assertEquals(WeatherResult.WeatherType.DROUGHT, result.getWeatherType());
    }

    @Test
    public void shouldReturnTransitionWeatherWhenSunIsNotInsideTriangle(){
        Mockito.when(triangle.areVertexesCollinear()).thenReturn(false);
        Mockito.when(triangle.isPointInside(Mockito.any())).thenReturn(false);

        WeatherResult result = positionsInterpreter.apply(solarSystemPositions);

        Assert.assertEquals(WeatherResult.WeatherType.TRANSITION, result.getWeatherType());
    }

    @Test
    public void shouldReturnRainyWeatherWhenSunIsInsideTriangle(){
        Mockito.when(triangle.areVertexesCollinear()).thenReturn(false);
        Mockito.when(triangle.isPointInside(Mockito.any())).thenReturn(true);

        WeatherResult result = positionsInterpreter.apply(solarSystemPositions);

        Assert.assertEquals(WeatherResult.WeatherType.RAINY, result.getWeatherType());
    }

    @Test
    public void shouldReturnRainyWithIntensityWeatherWhenSunIsInsideTriangle(){
        Mockito.when(triangle.areVertexesCollinear()).thenReturn(false);
        Mockito.when(triangle.isPointInside(Mockito.any())).thenReturn(true);
        Mockito.when(triangle.getPerimeter()).thenReturn(BigDecimal.TEN);

        RainyWeatherResult result = (RainyWeatherResult) positionsInterpreter.apply(solarSystemPositions);

        Assert.assertEquals(BigDecimal.TEN, result.getIntensity());
    }
}