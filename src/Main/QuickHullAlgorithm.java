/**
    QuickHull Algorithm is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; version 3 of the License.

    QuickHull Algorithm is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; If not, see <http://www.gnu.org/licenses/> or
    write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
    Boston, MA  02110-1301  USA

    Copyright (c) 2009 Jakob Westhoff <jakob@php.net> (PHP implementation)
    Copyright (c) 2014 Patrick Hillert <silent@gmx.biz> (Java ported code)

    @license GPLv3
 */

package main;

import java.util.Vector;

/**
 * This algorithm calculates the convex hull for a given set of points.
 * This is a Java port of the algorithm written in PHP by Jakob Westhoff.
 */
public class QuickHullAlgorithm {

    /**
     * The set of points to use in the calculation.
     */
    private Vector<Point> inputPoints;

    /**
     * The resulting convex hull points after the algorithm has been executed.
     */
    private Vector<Point> hullPoints;

    /**
     * The maximum right point.
     */
    private Point pointMaxX;

    /**
     * The maximum left point.
     */
    private Point pointMinX;

    /**
     * Anonymous inner class which encapsulates a point and a distance.
     */
    private class PointDistance {
        /**
         * The point.
         */
        protected Point p;

        /**
         * The distance to that point.
         */
        protected double distance;

        /**
         * Creates a new PointDistance.
         *
         * @param point The point.
         * @param dist The distance to the given point.
         */
        PointDistance(final Point point, final double dist) {
            this.p = point;
            this.distance = dist;
        }
    };

    /**
     * Initializes the QuickHull algorithm with a point cloud.
     *
     * @param points The point cloud to put in.
     */
    QuickHullAlgorithm(final Vector<Point> points) {
        inputPoints = points;
        hullPoints = new Vector<Point>(); // initial empty
    }

    /**
     * Returns the most right/left aligned point.
     *
     * @param leftside True if the most right aligned point is needed. False
     * otherwise.
     * @return The point which corresponds to the query.
     */
    private Point getXPoint(final boolean leftside) {
        Point pivot = inputPoints.firstElement();

        for (Point p : inputPoints) {
            if ((leftside && p.getX() < pivot.getX()) /* min search */
                   || (!leftside && p.getX() > pivot.getX()) /* max search */) {
                pivot = p;
            }
        }

        return pivot;
    }

    /**
     * Starts the algorithm to get all the hull points.
     *
     * @return A vector containing all convex hull points.
     */
    public final Vector<Point> getHullPoints() {
        if (hullPoints.size() == 0) {
            // This is the first run with the maximum and minimum x value
            // points. The points are definitive points of the convex hull.
            pointMaxX = getXPoint(false);
            pointMinX = getXPoint(true);

            // We must process both sides of the line, so we *merge* it here.
            hullPoints.addAll(quickHull(inputPoints, pointMinX, pointMaxX));
            hullPoints.addAll(quickHull(inputPoints, pointMaxX, pointMinX));
        }

        return hullPoints;
    }

    /**
     * Calculates the distance between two points which span a line and a point.
     *
     * The returned value is not the correct distance (for performance reasons)
     * value, but it's sufficient to determine the point with the maximum
     * distance between the line and the point. The result is a pseudo cross
     * product of the line vector (first to second point).
     *
     * Note: result < 0: point is right of the given vector (line)
     *       result > 0: point is left of the given vector (line)
     *       result = 0: point is on the line (colinear)
     *
     * @param pointToCalcDistanceTo The point to calculate the distance to.
     * @param firstLinePoint The first (start) point of the line.
     * @param secondLinePoint The second (end) point of the line.
     * @return The distance between the line and the given point.
     */
    private double calcDistance(final Point pointToCalcDistanceTo,
            final Point firstLinePoint, final Point secondLinePoint) {
        double[] vLine = new double[2];
        vLine[0] = secondLinePoint.getX() - firstLinePoint.getX();
        vLine[1] = secondLinePoint.getY() - firstLinePoint.getY();

        double[] vPoint = new double[2];
        vPoint[0] = pointToCalcDistanceTo.getX() - firstLinePoint.getX();
        vPoint[1] = pointToCalcDistanceTo.getY() - firstLinePoint.getY();

        return ((vPoint[1] * vLine[0]) - (vPoint[0] * vLine[1]));
    }

    /**
     * Calculates the distance between the line (spanned by the first and second
     * point) and *all* the given points.
     *
     * Note: Only the points left of the line will be returned. Every point
     * right of the line or colinear to the line will be deleted.
     *
     * @param points The points to calculate the distance to.
     * @param first The first point of the line.
     * @param second The second point of the line.
     * @return A vector containing all point distances.
     */
    private Vector<PointDistance> getPointDistances(final Vector<Point> points,
            final Point first, final Point second) {
        Vector<PointDistance> pointDistanceSet = new Vector<PointDistance>();

        for (Point p : points) {
            double distanceToPoint = calcDistance(p, first, second);

            if (distanceToPoint > 0) {
                pointDistanceSet.add(new PointDistance(p, distanceToPoint));
            } else {
                continue;
            }
        }

        return pointDistanceSet;
    }

    /**
     * Searches for the point which has the maximum distance.
     *
     * @param pointDistanceSet The point distance set to search in.
     * @return The point with the maximum distance to the given line.
     */
    private Point getPointWithMaximumDistanceFromLine(
            final Vector<PointDistance> pointDistanceSet) {
        double maxDistance = 0;
        Point maxPoint = null;

        for (PointDistance pd : pointDistanceSet) {
            if (pd.distance > maxDistance) {
                maxDistance = pd.distance;
                maxPoint = pd.p;
            }
        }

        return maxPoint;
    }

    /**
     * Runs the QuickHull algorithm on the given points. The other two points
     * given are used to delimit the search space in left and right side parts.
     *
     * Only the points left of the line will be inspected.
     *
     * @param points The set of points to analyze.
     * @param first The first point of the line which is left of the right one.
     * @param second The second point of the line which is right of the first
     * one.
     * @return A vector containing all the convex hull points on one side.
     */
    private Vector<Point> quickHull(final Vector<Point> points,
            final Point first, final Point second) {
        Vector<PointDistance> pointsLeftOfLine = getPointDistances(points,
            first, second);
        Point newMaxPoint = getPointWithMaximumDistanceFromLine(
            pointsLeftOfLine);

        Vector<Point> pointsToReturn = new Vector<Point>();
        if (newMaxPoint == null) {
            pointsToReturn.add(second);
        } else {
            Vector<Point> newPoints = new Vector<Point>();
            for (PointDistance pd : pointsLeftOfLine) {
                newPoints.add(pd.p);
            }

            pointsToReturn.addAll(quickHull(newPoints, first, newMaxPoint));
            pointsToReturn.addAll(quickHull(newPoints, newMaxPoint, second));
        }

        return pointsToReturn;
    }
}