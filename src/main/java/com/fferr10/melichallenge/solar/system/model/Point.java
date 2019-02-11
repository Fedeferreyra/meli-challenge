package com.fferr10.melichallenge.solar.system.model;

import com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter;

import java.math.BigDecimal;
import java.util.Objects;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;

public class Point {

    private BigDecimal x;
    private BigDecimal y;

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal calculateDistanceTo(Point b) {
        BigDecimal xDistance = b.x.subtract(this.x);
        BigDecimal yDistance = b.y.subtract(this.y);

        return toBigDecimal(Math.sqrt(Math.pow(xDistance.doubleValue(), 2) +
                Math.pow(yDistance.doubleValue(), 2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
