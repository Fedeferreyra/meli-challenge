package com.fferr10.melichallenge.solar.system.model;


public class Planet {

    private final String name;
    private final int angularSpeed;
    private final int distanceToSun;

    public Planet(String name, int angularSpeed, int distanceToSun) {
        this.name = name;
        this.angularSpeed = angularSpeed;
        this.distanceToSun = distanceToSun;
    }

    public String getName() {
        return name;
    }

    public int getAngularSpeed() {
        return angularSpeed;
    }

    public int getDistanceToSun() {
        return distanceToSun;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", angularSpeed=" + angularSpeed +
                ", distanceToSun=" + distanceToSun +
                '}';
    }
}
