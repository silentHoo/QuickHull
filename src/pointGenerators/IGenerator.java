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

import java.util.ArrayList;

import main.Point;

/**
 * Provides an IGenerator interface to implement different scenarios for
 * point generation.
 */
public interface IGenerator {
    /**
     * Runs the generation process.
     */
    void run();

    /**
     * Returns all the points that are generated.
     * Be sure to first execute run() to generate points.
     *
     * @see run() Execute run() before you get these points.
     * @return The generated points.
     */
    ArrayList<Point> getPoints();
}
