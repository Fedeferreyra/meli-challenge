package com.fferr10.melichallenge.solar.system.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolarSystemTest {

    private SolarSystem solarSystem;

    @Before
    public void setUp() {
        solarSystem = new SolarSystem();
    }

    @Test
    public void shouldReturnSlowestPlanetWhenCallGetSlowestPlanet() {
        String expectedResult = "Farengi";
        Assert.assertEquals(expectedResult, solarSystem.getSlowestPlanet().getName());
    }
}