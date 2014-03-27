package PointProcessors;

import java.util.Vector;

import Main.Point2D;

public class Random implements IProcessor {
	private static Vector<Point2D> points;
	private int numberOfPoints, maxX, maxY;
	
	public Random(int numberOfPoints, int maxX, int maxY) {
		this.numberOfPoints = numberOfPoints;
		this.maxX = maxX;
		this.maxY = maxY;
		
		points = new Vector<Point2D>();
	}
	
	public void run() {
		while (numberOfPoints > 0) {
			double x = Math.random() * maxX;
			double y = Math.random() * maxY;
			
			points.add(new Point2D(x, y));
			
			--numberOfPoints;
		}
	}
	
	public Vector<Point2D> getPoints() {
		return points;
	}
}
