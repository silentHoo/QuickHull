import static org.junit.Assert.*;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Main.Point2D;
import PointProcessors.*;

public class PointProcessors {
	Vector<Point2D> pointsRandom, pointsCircle;
	int numberOfPoints = 5000;
	int maxX, maxY = 800;
	
	@Before
	public void initialize() {
		Random pointProcessorRandom = new Random(numberOfPoints, maxX, maxY);
		pointProcessorRandom.run();
		pointsRandom = pointProcessorRandom.getPoints();
		
		Random pointProcessorCircle = new Random(numberOfPoints, maxX, maxY);
		pointProcessorCircle.run();
		pointsCircle = pointProcessorCircle.getPoints();
	}
	
	@Test
	public void RandomNumberOfPointsToGenerateEqualsNumberOfGeneratedPoints() {
		assertEquals(pointsRandom.size(), numberOfPoints);
	}

	@Test
	public void RandomPointsAreNotOutOfRange() {
		for (int i = 0; i < numberOfPoints; i++) {
			Point2D p = pointsRandom.elementAt(i);
			assertEquals(p.getX() >= 0 && p.getX() <= maxX && p.getY() >= 0 && p.getY() <= maxY, true);
		}
	}
	
	@Test
	public void CircleNumberOfPointsToGenerateEqualsNumberOfGeneratedPoints() {
		assertEquals(pointsCircle.size(), numberOfPoints);
	}

	@Test
	public void CirclePointsAreNotOutOfRange() {
		for (int i = 0; i < numberOfPoints; i++) {
			Point2D p = pointsCircle.elementAt(i);
			assertEquals(p.getX() >= 0 && p.getX() <= maxX && p.getY() >= 0 && p.getY() <= maxY, true);
		}
	}
}
