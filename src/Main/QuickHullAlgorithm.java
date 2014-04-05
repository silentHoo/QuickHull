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

    private static final Object[] Array = null;
    private Vector<Point> inputPoints;
    private Vector<Point> hullPoints;
    private Point pointMaxX, pointMinX;
    private Object p;

    private class PointDistance {
        protected Point p;
        protected double distance;
        
        PointDistance(Point p, double distance) {
            this.p = p;
            this.distance = distance;
        }
    };
    
    QuickHullAlgorithm(Vector<Point> points) {
        inputPoints = points;
        hullPoints = new Vector<Point>(); // empty
    }
    
    private Point getXPoint(boolean minimumSearch) {
        Point pivot = inputPoints.firstElement();
        
        // for each
        for (Point p : inputPoints) {
            if ( (minimumSearch && p.getX() < pivot.getX()) /* minimum search */
                    || (!minimumSearch && p.getX() > pivot.getX()) /* maximum search */) {
                pivot = p;
            }
        }
        
        return pivot;
    }
    
    public Vector<Point> getHullPoints() {
        if (hullPoints.size() == 0) {
            // This is the first run with the maximum and minimum x value
            // points. The points are definitive points of the convex hull.
            pointMaxX = getXPoint(false);
            pointMinX = getXPoint(true);
            
            // We must process both sides of the line, so we merge it after.
            hullPoints.addAll(quickHull(inputPoints, pointMinX, pointMaxX));
            hullPoints.addAll(quickHull(inputPoints, pointMaxX, pointMinX));
        }
        
        return hullPoints;
    }
    
    private double calcDistance(Point pointToCalcDistanceTo, Point firstLinePoint, Point secondLinePoint) {
        double[] vLine = new double[2];
        vLine[0] = secondLinePoint.getX() - firstLinePoint.getX();
        vLine[1] = secondLinePoint.getY() - firstLinePoint.getY();
        
        double[] vPoint = new double[2];
        vPoint[0] = pointToCalcDistanceTo.getX() - firstLinePoint.getX();
        vPoint[1] = pointToCalcDistanceTo.getY() - firstLinePoint.getY();
        
        return ((vPoint[1] * vLine[0]) - (vPoint[0] * vLine[1]));
    }
    
    private Vector<PointDistance> getPointDistances(Vector<Point> points, Point first, Point second) {
        Vector<PointDistance> pointDistanceSet = new Vector<PointDistance>();
        
        for (Point p : points) {
            double distanceToPoint = calcDistance(p, first, second);
            
            if (distanceToPoint > 0) {
                pointDistanceSet.add( new PointDistance(p, distanceToPoint) );
            } else {
                continue;
            }
        }
        
        return pointDistanceSet;
    }
    
    private Point getPointWithMaximumDistanceFromLine(Vector<PointDistance> pointDistanceSet) {
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
     * Runs the QuickHull algorithm on the given points.
     * The other two points given are used to delimit the search space in left and
     * right side parts.
     * 
     * Only the points left of the line will be inspected.
     * 
     * @param points The set of points to analyze.
     * @param first The first point of the line which is left of the right one. 
     * @param second The second point of the line which is right of the first one.
     * @return
     */
    private Vector<Point> quickHull(Vector<Point> points, Point first, Point second) {
        Vector<PointDistance> pointsLeftOfLine = getPointDistances(points, first, second);
        Point newMaximalPoint = getPointWithMaximumDistanceFromLine(pointsLeftOfLine);
        
        Vector<Point> pointsToReturn = new Vector<Point>();
        if (newMaximalPoint == null) {
            pointsToReturn.add(second);
        } else {
            Vector<Point> newPoints = new Vector<Point>();
            for (PointDistance pd : pointsLeftOfLine) {
                newPoints.add(pd.p);
            }
            
            pointsToReturn.addAll(quickHull(newPoints, first, newMaximalPoint));
            pointsToReturn.addAll(quickHull(newPoints, newMaximalPoint, second));
        }
    
        return pointsToReturn;
    }
}