package PointProcessors;

import java.util.Vector;

import Main.Point2D;

public class Circle implements IProcessor {
	private static Vector<Point2D> points;
	private int numberOfPoints, radius;
	
	public Circle(int numberOfPoints, int maxX, int maxY) {
		this.numberOfPoints = numberOfPoints;
		radius = (int)Math.floor(Math.min(maxX, maxY) / 2);
		
		points = new Vector<Point2D>();
	}
	
	public void run() {
		double angleInDegree = 360.0 / (double)(numberOfPoints);
		
		while (numberOfPoints > 0) {
			double angleInRadian = angleInDegree * (Math.PI / 180.0);
			double x = (double)(radius * Math.cos(angleInRadian)) + radius /* origin */;
			double y = (double)(radius * Math.cos(angleInRadian)) + radius /* origin */;
			
			points.add(new Point2D(x, y));
			
			--numberOfPoints;
		}
	}
	
	public Vector<Point2D> getPoints() {
		return points;
	}
}
