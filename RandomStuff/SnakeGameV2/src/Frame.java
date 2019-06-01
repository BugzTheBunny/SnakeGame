import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public static void startWindow() {
		JFrame frame = new JFrame();
		Gameplay gameplay = new Gameplay();
		// The empty string in the title is to put the title in the middle
		frame.setTitle("                    " + "                    " + "                    " + "                    "
				+ "                    " + "A mini snake game project, by Slava");
		frame.setBounds(10, 10, 905, 700);
		// background color
		frame.setBackground(Color.DARK_GRAY);
		// No allowing the user to resize the window
		frame.setResizable(false);
		// visibility
		frame.setVisible(true);
		// what will happend on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameplay);
	}

}
