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

import java.util.ArrayList;

import pointgenerators.*;

/**
 * This class provides methods to benchmark the algorithms performance for
 * several number of points in the best and average case.
 */
public class Benchmark {
    /**
     * The width of the bounding box / volume to generate points in.
     */
    private static final int WIDTH = 10000;

    /**
     * The height of the bounding box / volume to generate points in.
     */
    private static final int HEIGHT = 10000;

    /**
     * The number of points to start benchmarking with.
     */
    private static final int NUMBER_POINTS_FROM = 500;

    /**
     * The number of points to end benchmarking with.
     */
    private static final int NUMBER_POINTS_TO = 1024000;

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
    private static final int BENCHMARK_ROUNDS = 1000;
    /**
     * The JFrame wrapper to show information.
     */
    private Frame frame;

    /**
     * The type of the benchmark.
     */
    public enum BenchmarkType {
        /**
         * 2D Best Case.
         */
        BEST_CASE_2D,

        /**
         * 2D average Case.
         */
        AVG_CASE_2D
    };

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
        frame.updateLabel("1) Performing Benchmark for: 2D Best Case");
        checkPerformance(BenchmarkType.BEST_CASE_2D);

        frame.updateLabel("2) Performing Benchmark for: 2D Average Case");
        checkPerformance(BenchmarkType.AVG_CASE_2D);

        frame.updateLabel("================== DONE ==================");
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
     *
     *  @param type The benchmark type to check.
     */
    public final void checkPerformance(final BenchmarkType type) {
        double[] elapsedRoundTimes = new double[BENCHMARK_ROUNDS];
        double averageTimeLast = 0.0;

        for (int n = NUMBER_POINTS_FROM; n <= NUMBER_POINTS_TO; n = n * 2) {
            ArrayList<Point> points = getPointsByType(type, n);

            for (int i = 0; i < BENCHMARK_ROUNDS; i++) {
                    long startTime = System.nanoTime();

                    QuickHullAlgorithm qh = new QuickHullAlgorithm(points);
                    qh.getHullPoints();

                    elapsedRoundTimes[i] = ((System.nanoTime() - startTime)
                            / NANO_TO_MILLI);
            }

            double averageTime = 0.0;
            for (double rt : elapsedRoundTimes) {
                averageTime += rt;
            }
            averageTime /= elapsedRoundTimes.length;

            /*System.out.format("n = %06d\t t = %05f \t rounds = %d\t "
               + "ratio = %f\n",
                   n, averageTime, BENCHMARK_ROUNDS,
                   averageTime / averageTimeLast);*/

            System.out.format("%d \t & %.2f & \t %.2f \\\\ \n",
                        n, averageTime, (averageTime / averageTimeLast));

            averageTimeLast = averageTime;
        }
    }

    /**
     * Generates the given number of points for the given benchmark situation.
     *
     * @param type The benchmark type.
     * @param numberOfPoints The number of points to generate.
     * @return A vector containing all requested points.
     */
    private ArrayList<Point> getPointsByType(final BenchmarkType type,
            final int numberOfPoints) {
        IGenerator pointGenerator = null;

        if (type == BenchmarkType.BEST_CASE_2D) {
            pointGenerator = new Rectangle(numberOfPoints, WIDTH, HEIGHT);
        } else if (type == BenchmarkType.AVG_CASE_2D) {
            pointGenerator = new Circle(numberOfPoints, WIDTH, HEIGHT);
        }

        pointGenerator.run();

        return pointGenerator.getPoints();
    }
}
