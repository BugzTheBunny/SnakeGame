
import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;

public class Gameplay extends JPanel {
	private ImageIcon titleImage;

	public Gameplay() {

	}

	public void paint(Graphics graphics) {
		// Title image border.
		graphics.setColor(Color.BLACK);
		graphics.drawRect(25, 9, 850, 55);
		// Draw title image.
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, graphics, 25, 10);
		// Draw game borders.
		graphics.setColor(Color.BLACK);
		graphics.drawRect(23, 73, 853, 578);
		// Draw background for the gameplay.
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(25, 75, 850, 575);
	}

}
