package com.fferr10.melichallenge.solar.system.model.shape;

import com.fferr10.melichallenge.solar.system.model.Point;
import com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter;

import java.math.BigDecimal;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

public class Triangle extends Shape {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point... points) {
        this.a = points[0];
        this.b = points[1];
        this.c = points[2];
    }

    @Override
    public BigDecimal getArea() {
        BigDecimal ax = a.getX().multiply(b.getY().subtract(c.getY()));
        BigDecimal bx = b.getX().multiply(c.getY().subtract(a.getY()));
        BigDecimal cx = c.getX().multiply(a.getY().subtract(b.getY()));
        
        return toBigDecimal(Math.abs(0.5 * (ax.add(bx).add(cx).doubleValue())));
    }

    @Override
    public BigDecimal getPerimeter() {
        BigDecimal abSide = a.calculateDistanceTo(b);
        BigDecimal bcSide = b.calculateDistanceTo(c);
        BigDecimal caSide = c.calculateDistanceTo(a);
        return abSide.add(bcSide.add(caSide));
    }

    @Override
    public Boolean isPointInside(Point point) {
        BigDecimal abcArea = getArea();

        BigDecimal abPointArea = new Triangle(a, b, point).getArea();
        BigDecimal acPointArea = new Triangle(a, c, point).getArea();
        BigDecimal bcPointArea = new Triangle(b, c, point).getArea();

        return abPointArea.add(acPointArea .add(bcPointArea)).compareTo(abcArea) == 0;
    }

    @Override
    public Boolean areVertexesCollinear() {
        return getArea().compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    public Boolean areVertexesCollinearWithPoint(Point point) {

        Triangle triangle = new Triangle(a, b, point);
        return this.getArea().compareTo(BigDecimal.ZERO) == 0 && triangle.getArea().compareTo(BigDecimal.ZERO) == 0;
    }
}
