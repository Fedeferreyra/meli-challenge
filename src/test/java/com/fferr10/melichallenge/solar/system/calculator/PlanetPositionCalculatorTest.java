package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

public class PlanetPositionCalculatorTest extends TestFixture {

    private PlanetPositionCalculator planetPositionCalculator;

    @Before
    public void setUp() {
        planetPositionCalculator = new PlanetPositionCalculator(new PlanetAnglePositionsCalculator());
    }

    @Test
    public void shouldReturnOriginWhenCalledWithAllParametersAsZero() {
        Point result = planetPositionCalculator.calculate(allZeroPlanet, 0);

        Assert.assertEquals(origin, result);
    }

    @Test
    public void shouldReturnOriginWhenDistanceToSunIsZero() {
        Point result = planetPositionCalculator.calculate(zeroDistanceToSunPlanet, 3);

        Assert.assertEquals(origin, result);
    }

    @Test
    public void shouldReturnDistanceToSunOnXAxisWhenAnyOtherParameterThanDistanceToSunIsZero() {
        Point zeroAngularSpeedPlanetResult = planetPositionCalculator.calculate(zeroAngularSpeedPlanet, 1);
        Point zeroDayResult = planetPositionCalculator.calculate(allPositivePlanet, 0);

        Assert.assertEquals(zeroAngularSpeedPlanetResult, getPointWithX(toBigDecimal(zeroAngularSpeedPlanet.getDistanceToSun())));
        Assert.assertEquals(zeroDayResult, getPointWithX(toBigDecimal(allPositivePlanet.getDistanceToSun())));
    }

    @Test
    public void shouldReturnCorrectPointWhenCalledWithAllPositiveParameters() {
        Point result = planetPositionCalculator.calculate(allPositivePlanet, 1);

        Point expectedResult = new Point(toBigDecimal(187.93852418), toBigDecimal(68.40402859));

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnCorrectPointWhenCalledWithAllNegativeParameters(){
        Point result = planetPositionCalculator.calculate(allNegativePlanet, -3);

        Point expectedResult = new Point(toBigDecimal(-100.00000021), toBigDecimal(-173.20508064));

        Assert.assertEquals(expectedResult, result);
    }
}