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

package main;

import java.util.Vector;

import pointGenerators.*;

/**
 * This class provides methods to benchmark the algorithms performance for
 * several number of points in the best and average case.
 */
public class Benchmark {
    /**
     * The width of the bounding box / volume to generate points in.
     */
    private static final int WIDTH = 800;

    /**
     * The height of the bounding box / volume to generate points in.
     */
    private static final int HEIGHT = 800;

    /**
     * The number of points to start benchmarking with.
     */
    private static final int NUMBER_POINTS_FROM = 500;

    /**
     * The number of points to end benchmarking with.
     */
    private static final int NUMBER_POINTS_TO = 100000;

    /**
     * The factor to transform nano seconds to milli seconds.
     */
    private static final double NANO_TO_MILLI = 1000000.0;

    /**
     * The width of the window.
     */
    private static final int WINDOW_WIDTH = 300;

    /**
     * The height of the window.
     */
    private static final int WINDOW_HEIGHT = 100;

    /**
     * The number of rounds to run the whole algorithm on each point set.
     * Higher will result in a better average value on the elapsed time
     * taken.
     */
    private static final int BENCHMARK_ROUNDS = 5;
    /**
     * The JFrame wrapper to show information.
     */
    private Frame frame;

    /**
     * Initializes a new Benchmark set.
     */
    public Benchmark() {
        frame = new Frame("QuickHull benchmark", WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Runs the following performance checks.
     *
     *  - 2D best case (rectangle)
     *  - 2D average case (circle)
     */
    public final void runFullBenchmarkSuite() {
        frame.updateLabel("... running full benchmark suite ...");

        checkPerformance2DBestCase();
        checkPerformance2DAverageCase();

        frame.updateLabel("... done! ...");
    }

    /**
     * Measures the time of the QuickHull algorithm for several numbers of
     * points, beginning with n = 500, then n = 1000, n = 2000, ... (double
     * the points in every step).
     *
     * Checks the performance for:
     *  - 2D in the Best Case O(n)
     *  - Description: Rectangle with four points on the corners and an
     *    average amounts of random points inside the rectangle.
     */
    public final void checkPerformance2DBestCase() {
        frame.updateLabel("... running 2D best case ...");

        for (int n = NUMBER_POINTS_FROM; n <= NUMBER_POINTS_TO; n = n * 2) {
            Rectangle pointGenerator = new Rectangle(n, WIDTH, HEIGHT);
            pointGenerator.run();
            genericPerformanceCheck(pointGenerator.getPoints());
        }
    }

    /**
     * Measures the time of the QuickHull algorithm for several numbers of
     * points, beginning with n = 500, then n = 1000, n = 2000, ... (double
     * the points in every step).
     *
     * Checks the performance for:
     *  - 2D in the Average Case O(n log n)
     *  - Description: Circle with several points on the border of the circle
     *  with no points inside the circle.
     */
    public final void checkPerformance2DAverageCase() {
        frame.updateLabel("... running 2D best case ...");

        for (int n = NUMBER_POINTS_FROM; n <= NUMBER_POINTS_TO; n = n * 2) {
            Circle pointGenerator = new Circle(n, WIDTH, HEIGHT);
            pointGenerator.run();
            genericPerformanceCheck(pointGenerator.getPoints());
        }
    }

    /**
     * Runs the performance benchmark on the given set of points.
     * Every performance check is run the configured number of times
     * to get the average amount of time consumed by the algorithm and not
     * lack behind any garbage issues or something like that.
     *
     * @param points The points to run the benchmark on.
     */
    private void genericPerformanceCheck(final Vector<Point> points) {
        double[] elapsedRoundTimes = new double[BENCHMARK_ROUNDS];

        for (int i = 0; i < elapsedRoundTimes.length; i++) {
            long startTime = System.nanoTime();

            QuickHullAlgorithm qh = new QuickHullAlgorithm(points);
            /*Vector<Point> hullPoints = */qh.getHullPoints();

            elapsedRoundTimes[i] = ((System.nanoTime() - startTime)
                / NANO_TO_MILLI);
        }

        double averageTime = 0.0;
        for (double rt : elapsedRoundTimes) {
            averageTime += rt;
        }
        averageTime /= elapsedRoundTimes.length;

        System.out.format("n = %d, t = %d\n", points.size(), (int) averageTime);
    }
}
