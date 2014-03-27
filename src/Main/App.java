package Main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import PointProcessors.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class App {
	private static Frame frame;
	private static Vector<Point2D> points;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final int NUMER_OF_POINTS = 5000;
	
	public static void main(String[] args) {;
		frame = new Frame("QuickHull2D", WIDTH, HEIGHT);
		
		Random pointProcessor = new Random(NUMER_OF_POINTS, WIDTH, HEIGHT);
		pointProcessor.run();
		points = pointProcessor.getPoints();
		
		//QuickHull2D qh = new QuickHull2D();
	}
}
