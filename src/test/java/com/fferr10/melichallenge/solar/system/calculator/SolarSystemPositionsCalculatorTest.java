package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.model.SolarSystem;
import com.fferr10.melichallenge.solar.system.model.SolarSystemPositions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SolarSystemPositionsCalculatorTest extends TestFixture {

    private SolarSystemPositionsCalculator solarSystemPositionsCalculator;
    private SolarSystem solarSystem;
    @Mock
    PlanetPositionCalculator planetPositionCalculator;

    @Before
    public void setUp() {
        solarSystem = new SolarSystem();
        Mockito.when(planetPositionCalculator.calculate(Mockito.any(), Mockito.anyInt())).thenReturn(randomPoint());
        solarSystemPositionsCalculator = new SolarSystemPositionsCalculator(planetPositionCalculator);
    }

    @Test
    public void verifyPlanetPositionCalculatorIsCalledAsManyTimesAsPlanetsAre(){
        SolarSystemPositions result = solarSystemPositionsCalculator.calculate(solarSystem, 1);

        Mockito.verify(planetPositionCalculator, Mockito.times(solarSystem.getPlanets().size()))
                .calculate(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void shouldReturnSolarSystemPositionsWithAsManyPositionsAsSolarSystem() {
        SolarSystemPositions result = solarSystemPositionsCalculator.calculate(solarSystem, 3);

        Assert.assertEquals(result.getPlanetPoints().size(), 3);
    }


}