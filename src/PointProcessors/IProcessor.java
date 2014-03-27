package PointProcessors;

import java.util.Vector;

import Main.Point2D;

public interface IProcessor {
	public void run();
	public Vector<Point2D> getPoints();
}
