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

package pointGenerators;

import java.util.Vector;

import main.Point;

/**
 * Provides an IGenerator interface and implements methods to generate points
 * for the average case.
 */
public class Circle implements IGenerator {
    /**
     * The generated points for the scenario.
     */
    private static Vector<Point> points;

    /**
     * The number of points to generate.
     */
    private int pointsToGenerate;

    /**
     * The radius of the circle.
     */
    private int radius;

    /**
     * Initializes the new rectangle with the given parameters.
     *
     * @param numberOfPoints The amount of points to generate.
     * @param maxX The maximum width of the bounding box window.
     * @param maxY The maximum height of the bounding box window.
     */
    public Circle(final int numberOfPoints, final int maxX, final int maxY) {
        pointsToGenerate = numberOfPoints;
        radius = (int) Math.floor(Math.min(maxX, maxY) / 2);

        points = new Vector<Point>();
    }

    /**
     * Runs the generation process.
     */
    public final void run() {
        final double fullCircleDegree = 360.0;
        double angleInDegree = fullCircleDegree / (double) pointsToGenerate;

        int segmentCounter = 1;
        while (pointsToGenerate > 0) {
            double angleInRadian = segmentCounter * angleInDegree
                * (Math.PI / (fullCircleDegree / 2.0));
            double x = (double) (radius * Math.cos(angleInRadian))
                + radius /* origin */;
            double y = (double) (radius * Math.sin(angleInRadian))
                + radius /* origin */;

            points.add(new Point(x, y));

            --pointsToGenerate;
            ++segmentCounter;
        }
    }

    /**
     * Returns all the points that are generated.
     * Be sure to first execute run() to generate points.
     *
     * @see run() Execute run() before you get these points.
     * @return The generated points.
     */
    public final Vector<Point> getPoints() {
        return points;
    }
}
