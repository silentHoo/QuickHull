package Main;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Frame extends JFrame {
	private static final long serialVersionUID = 2190991212981210373L;
	private static JLabel textLabel;
	
	Frame(String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
		textLabel = new JLabel("Nothing here.", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(300, 100)); 
		frame.getContentPane().add(textLabel, BorderLayout.CENTER); 
		 
		// display the window
		frame.setLocationRelativeTo(null); 
		frame.pack();
		frame.setVisible(true); 
	}
	
	public void updateLabel(String text) {
		textLabel.setText(text);
	}
}
