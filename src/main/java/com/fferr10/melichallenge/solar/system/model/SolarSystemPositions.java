package com.fferr10.melichallenge.solar.system.model;

import java.util.List;

public class SolarSystemPositions {

    private final List<Point> planetPoints;
    private final Point sunPoint;

    public SolarSystemPositions(List<Point> planetPoints, Point sunPoint) {
        this.planetPoints = planetPoints;
        this.sunPoint = sunPoint;
    }

    public List<Point> getPlanetPoints() {
        return planetPoints;
    }

    public Point getSunPoint() {
        return sunPoint;
    }



}
