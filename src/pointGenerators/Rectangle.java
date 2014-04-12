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

package pointgenerators;

import java.util.ArrayList;

import main.Point;

/**
 * Provides an IGenerator interface and implements methods to generate points
 * for the best case.
 */
public class Rectangle implements IGenerator {
    /**
     * The generated points for the scenario.
     */
    private static ArrayList<Point> points;

    /**
     * The number of points to generate.
     */
    private int pointsToGenerate;

    /**
     * The bounding box dimension along the x-axis.
     */
    private int width;

    /**
     * The bounding box dimension along the y-axis.
     */
    private int height;

    /**
     * Initializes the new rectangle with the given parameters.
     *
     * @param numberOfPoints The amount of points to generate.
     * @param maxX The maximum width of the bounding box window.
     * @param maxY The maximum height of the bounding box window.
     */
    public Rectangle(final int numberOfPoints, final int maxX, final int maxY) {
        pointsToGenerate = numberOfPoints;
        width = maxX;
        height = maxY;

        points = new ArrayList<Point>();
    }

    /**
     * Runs the generation process.
     */
    public final void run() {
        // four corners
        points.add(new Point(0, 0));
        points.add(new Point(width, 0));
        points.add(new Point(0, height));
        points.add(new Point(width, height));

        pointsToGenerate -= 4;

        // all points IN the rectangle
        while (pointsToGenerate > 0) {
            double x, y = 0;
            do {
                x = Math.random() * width;
                y = Math.random() * height;
            } while(x == 0 || x == width || y == 0 || y == height);

            points.add(new Point(x, y));

            --pointsToGenerate;
        }
    }

    /**
     * Returns all the points that are generated.
     * Be sure to first execute run() to generate points.
     *
     * @see run() Execute run() before you get these points.
     * @return The generated points.
     */
    public final ArrayList<Point> getPoints() {
        return points;
    }
}
