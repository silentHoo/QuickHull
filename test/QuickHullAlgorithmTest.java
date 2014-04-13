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

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Point;
import main.QuickHullAlgorithm;
import org.junit.Test;

/**
 * Test class for the QuickHull algorithm.
 */
public class QuickHullAlgorithmTest {

    /**
     * Test if three points placed as a triangle return
     * three hull points.
     */
    @Test
    public final void check2DRecursiveForThreeHullPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points);
        ArrayList<Point> outPoints = qh.getHullPoints();

        assertEquals(3, outPoints.size());
    }

    /**
     * Test if four points placed as a rectangle return
     * four hull points.
     */
    @Test
    public final void check2DRecursiveForFourHullPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 1));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points);
        ArrayList<Point> outPoints = qh.getHullPoints();

        assertEquals(4, outPoints.size());
    }

    /**
     * Test if four points placed as a rectangle return
     * four hull points.
     */
    @Test
    public final void check2DRecursiveForSomeHullPointsWithInnerPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        // outer points
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(10, 10));
        points.add(new Point(0, 10));

        // inner points (diagonal)
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        // inner points (somewhere)
        points.add(new Point(5, 2));
        points.add(new Point(1, 9));
        points.add(new Point(9, 8));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points);
        ArrayList<Point> outPoints = qh.getHullPoints();

        // only four points must be in the hull list
        assertEquals(4, outPoints.size());
    }
    
    /**
     * Test if three points placed as a triangle return
     * three hull points.
     */
    @Test
    public final void check2DIterativeForThreeHullPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points, true);
        ArrayList<Point> outPoints = qh.getHullPoints();

        assertEquals(3, outPoints.size());
    }

    /**
     * Test if four points placed as a rectangle return
     * four hull points.
     */
    @Test
    public final void check2DIterativeForFourHullPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 1));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points, true);
        ArrayList<Point> outPoints = qh.getHullPoints();

        assertEquals(4, outPoints.size());
    }

    /**
     * Test if four points placed as a rectangle return
     * four hull points.
     */
    @Test
    public final void check2DIterativeForSomeHullPointsWithInnerPoints() {
        ArrayList<Point> points = new ArrayList<Point>();

        // outer points
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(10, 10));
        points.add(new Point(0, 10));

        // inner points (diagonal)
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        // inner points (somewhere)
        points.add(new Point(5, 2));
        points.add(new Point(1, 9));
        points.add(new Point(9, 8));

        QuickHullAlgorithm qh = new QuickHullAlgorithm(points, true);
        ArrayList<Point> outPoints = qh.getHullPoints();

        // only four points must be in the hull list
        assertEquals(4, outPoints.size());
    }
}
