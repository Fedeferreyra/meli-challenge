package com.fferr10.melichallenge.solar.system.model.shape;

import com.fferr10.melichallenge.solar.system.model.Point;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Shape {

    private static final Map<Integer, Class> shapesMap;

    static {
        shapesMap = new HashMap<>();
        shapesMap.put(3, Triangle.class);
    }

    public static Shape build(List<Point> points) {
        try {
            Point[] pointsArray = new Point[points.size()];
            pointsArray = points.toArray(pointsArray);
            return (Shape) shapesMap.get(points.size()).getConstructor(Point[].class).newInstance((Object) pointsArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Triangle((Point[]) points.toArray());
    }

    public abstract BigDecimal getArea();

    public abstract BigDecimal getPerimeter();

    public abstract Boolean isPointInside(Point point);

    public abstract Boolean areVertexesCollinear();

    public abstract Boolean areVertexesCollinearWithPoint(Point point);
}
