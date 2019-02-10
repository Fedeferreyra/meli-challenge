package com.fferr10.melichallenge.solar.system.model;

public class Point {

    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double calculateDistanceTo(Point b){
        Double xDistance = b.x - this.x;
        Double yDistance = b.y - this.y;

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }
}
