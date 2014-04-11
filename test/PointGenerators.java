/**
    Copyright (c) 2014 Patrick Hillert <silent@gmx.biz>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// Java dependencies
import java.util.Vector;

import static org.junit.Assert.*;
import main.Point;

import org.junit.Before;
import org.junit.Test;

import pointGenerators.*;

/**
 * This class provides methods for testing the PointGenerators.
 *
 * @author Patrick Hillert <silent@gmx.biz>
 */
public class PointGenerators {
    /**
     * Points for the best case (rectangle).
     */
    private Vector<Point> pointsRectangle;

    /**
     * Points for the average case (circle).
     */
    private Vector<Point> pointsCircle;

    /**
     * Points to generate for each case.
     */
    private final int numberOfPoints = 500;

    /**
     * Range for the points to generate points for.
     */
    private final int cMaxX = 800, cMaxY = 800;

    /**
     * Generates points to test with the algorithm before any test starts.
     */
    @Before
    public final void initialize() {
        // generate points for the best case (points inside the rectangle + 4
        // corner points)
        Rectangle pointProcessorRectangle = new Rectangle(numberOfPoints,
                                                                  cMaxX, cMaxY);
        pointProcessorRectangle.run();
        pointsRectangle = pointProcessorRectangle.getPoints();

        // generate points for the average case (points on the circle border)
        Circle pointProcessorCircle = new Circle(numberOfPoints, cMaxX, cMaxY);
        pointProcessorCircle.run();
        pointsCircle = pointProcessorCircle.getPoints();
    }

    /**
     * Checks if the number of generated points matches the number of points we
     * wanted to generate for the best case scenario.
     */
    @Test
    public final void rectangleNumberOfPointsEqualsNumberOfGeneratedPoints() {
        assertEquals(pointsRectangle.size(), numberOfPoints);
    }

    /**
     * Checks if the generated points (for the best case) are not outside of
     * the given bounding box.
     */
    @Test
    public final void rectanglePointsAreNotOutOfRange() {
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = pointsRectangle.elementAt(i);

            // x between 0 and MAX_X
            assertTrue(p.getX() >= 0 && p.getX() <= cMaxX);

            // y between 0 and MAX_Y
            assertTrue(p.getY() >= 0 && p.getY() <= cMaxY);
        }
    }

    /**
     * Checks if only four points lying on the edge.
     */
    @Test
    public final void rectangleOnly4RectanglesLyingOnEdge() {
        final int maxPoints = 4;
        int edgeCountX = 0;
        int edgeCountY = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            Point p = pointsRectangle.elementAt(i);

            if (p.getX() == cMaxX || p.getX() == 0) {
                ++edgeCountX;
            }

            if (p.getY() == cMaxY || p.getY() == 0) {
                ++edgeCountY;
            }
        }

        // only four x points are on the edge of the rectangle
        assertEquals(edgeCountX, maxPoints);

        // only four y points are on the edge of the rectangle
        assertEquals(edgeCountY, maxPoints);
    }

    /**
     * Checks if the number of generated points matches the number of points we
     * wanted to generate for the average case scenario.
     */
    @Test
    public final void circleNumberOfPointsEqualsNumberOfGeneratedPoints() {
        assertEquals(pointsCircle.size(), numberOfPoints);
    }

    /**
     * Checks if the generated points (for the average case) are not outside of
     * the given bounding box.
     */
    @Test
    public final void circlePointsAreNotOutOfRange() {
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = pointsCircle.elementAt(i);
            assertTrue(p.getX() >= 0 && p.getX() <= cMaxX
                && p.getY() >= 0 && p.getY() <= cMaxY);
        }
    }
}
