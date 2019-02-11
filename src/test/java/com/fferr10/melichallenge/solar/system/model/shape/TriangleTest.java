package com.fferr10.melichallenge.solar.system.model.shape;

import com.fferr10.melichallenge.solar.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.model.Point;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TriangleTest extends TestFixture {

    private Triangle triangle;
    private Triangle line;

    @Before
    public void setUp() {
        Point a = getPointWithX(toBigDecimal(2));
        Point b = getPointWithX(toBigDecimal(-2));
        Point c = getPointWithY(toBigDecimal(2));
        triangle = new Triangle(a, b, c);
        line = new Triangle(a, b, origin);
    }

    @Test
    public void shouldReturnCorrectValueWhenGetAreaIsCalled() {
        BigDecimal result = triangle.getArea();

        BigDecimal expectedResult = toBigDecimal(4);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnTrueWhenIsPointInsideIsCalled() {
        Boolean pointInside = triangle.isPointInside(origin);

        assertTrue(pointInside);
    }

    @Test
    public void shouldReturnFalseWhenIsPointInsideIsCalled(){
        Boolean pointInside = line.isPointInside(getPointWithY(toBigDecimal(2)));

        assertTrue(!pointInside);
    }

    @Test
    public void shouldReturnCorrectValueWhenGetPerimeterIsCalled() {
        BigDecimal result = triangle.getPerimeter();

        BigDecimal expectedResult = toBigDecimal(9.65685424);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnFalseWhenAreVertexesCollinearIsCalled() {
        Boolean result = triangle.areVertexesCollinear();

        assertTrue(!result);
    }

    @Test
    public void shouldReturnTrueWhenAreVertexesCollinearIsCalled(){
        Boolean result = line.areVertexesCollinear();

        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenAreVertexesCollinearWithPointIsCalled(){
        Boolean result = line.areVertexesCollinearWithPoint(origin);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenAreVertexesCollinearWithPointIsCalled() {
        Boolean result = triangle.areVertexesCollinearWithPoint(origin);

        assertTrue(!result);
    }
}