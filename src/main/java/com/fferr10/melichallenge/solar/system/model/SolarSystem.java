package com.fferr10.melichallenge.solar.system.model;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class SolarSystem {

    private List<Planet> planets;
    private final Point sunPoint = new Point(BigDecimal.ZERO, BigDecimal.ZERO);

    public SolarSystem() {
        Planet farengi = new Planet("Farengi", 1, 500);
        Planet betasoide = new Planet("Betasoide", 3, 2000);
        Planet vulcano = new Planet("Vulcano", -5, 1000);
        planets = Lists.newArrayList(farengi, betasoide, vulcano);
        planets.sort((o1, o2) -> Math.abs(o1.getAngularSpeed()) < Math.abs(o2.getAngularSpeed()) ? o1.getAngularSpeed() : o2.getAngularSpeed());
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Point getSunPoint() {
        return sunPoint;
    }

    public Planet getSlowestPlanet() {
        return planets.get(0);
    }
}
