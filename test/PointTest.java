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

import main.Point;

import org.junit.Test;

/**
 * Test class for the points.
 */
public class PointTest {

    /**
     * Tests the 2D point.
     */
    @Test
    public final void createAndGetValues2D() {
        Point p = new Point(1.0, 2.0);

        assertTrue(p.getX() == 1.0);
        assertTrue(p.getY() == 2.0);
    }

    /**
     * Tests the 3D point.
     */
    @Test
    public final void createAndGetValues3D() {
        Point p = new Point(1.0, 2.0, 3.0);

        assertTrue(p.getX() == 1.0);
        assertTrue(p.getY() == 2.0);
        assertTrue(p.getZ() == 3.0);
    }
}
