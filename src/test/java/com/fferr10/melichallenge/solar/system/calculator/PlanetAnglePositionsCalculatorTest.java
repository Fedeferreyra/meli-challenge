package com.fferr10.melichallenge.solar.system.calculator;

import com.fferr10.melichallenge.solar.system.model.Planet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

public class PlanetAnglePositionsCalculatorTest {

    private PlanetAnglePositionsCalculator planetAnglePositionsCalculator;

    @Before
    public void setUp() {
        planetAnglePositionsCalculator = new PlanetAnglePositionsCalculator();
    }

    @Test
    public void shouldAlwaysReturnValueBetweenTwoPiBelowZeroAndTwoPi() {
        Planet planet = new Planet("test", new Random(2).nextInt(), new Random().nextInt());
        BigDecimal result = planetAnglePositionsCalculator.calculate(planet, 2);

        Assert.assertTrue(result.compareTo(toBigDecimal(-1 * (2 * Math.PI))) >= 0 &&
                result.compareTo(toBigDecimal(2 * Math.PI)) <= 0);
    }

    @Test
    public void shouldReturnCorrectValueWhenCalledWithAllPositiveParameters() {
        Planet planet = new Planet("test", 20, 200);
        BigDecimal result = planetAnglePositionsCalculator.calculate(planet, 3);

        BigDecimal expectedResult = toBigDecimal(1.04719755);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnCorrectValueWhenCalledWithNegativeAngularSpeed() {
        Planet planet = new Planet("test", -20, 200);

        BigDecimal result = planetAnglePositionsCalculator.calculate(planet, 3);

        BigDecimal expectedResult = toBigDecimal(-1.04719755);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnCorrectValueWhenCalledWithNegativeDay() {
        Planet planet = new Planet("test", 20, 300);
        BigDecimal result = planetAnglePositionsCalculator.calculate(planet, -3);

        BigDecimal expectedResult = toBigDecimal(-1.04719755);

        Assert.assertEquals(expectedResult, result);
    }
}