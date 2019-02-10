package com.fferr10.melichallenge.solar.system.model.shape;

import com.fferr10.melichallenge.solar.system.model.Point;

class Triangle extends Shape {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point... points) {
        this.a = points[0];
        this.b = points[1];
        this.c = points[2];
    }

    @Override
    public Double getArea() {
        return Math.abs(0.5 * (a.getX() * (b.getY() - c.getY()) +
                b.getX() * (c.getY() - a.getY()) +
                c.getX() * (a.getY() - b.getY())));
    }

    @Override
    public Double getPerimeter() {
        Double abSide = a.calculateDistanceTo(b);
        Double bcSide = b.calculateDistanceTo(c);
        Double caSide = c.calculateDistanceTo(a);
        return abSide + bcSide + caSide;
    }

    @Override
    public Boolean isPointInside(Point point) {
        Double abcArea = getArea();

        Double abPointArea = new Triangle(a, b, point).getArea();
        Double acPointArea = new Triangle(a, c, point).getArea();
        Double bcPointArea = new Triangle(b, c, point).getArea();

        return abPointArea + acPointArea + bcPointArea == abcArea;
    }

    @Override
    public Boolean areVertexesCollinear() {
        return getArea() == 0;
    }

    @Override
    public Boolean areVertexesCollinearWithPoint(Point point) {
        Triangle triangle = new Triangle(a, b, point);
        return triangle.getArea() == 0;
    }
}
