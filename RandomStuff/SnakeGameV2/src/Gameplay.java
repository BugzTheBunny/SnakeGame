
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	private int moves = 0;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int lenthOfSnake = 3;
	private ImageIcon rightMount, downMount, upMount, leftMount, snakeImage, titleImage;
	private Timer timer;
	private int delay = 25;

	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics graphics) {

		if (moves == 0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;

			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
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

		rightMount = new ImageIcon("rightmouth.png");
		rightMount.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);

		for (int i = 0; i < lenthOfSnake; i++) {
			if (i == 0 && right) {
				rightMount = new ImageIcon("rightmouth.png");
				rightMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && left) {
				leftMount = new ImageIcon("leftmouth.png");
				leftMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && down) {
				downMount = new ImageIcon("downmouth.png");
				downMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && up) {
				upMount = new ImageIcon("upmouth.png");
				upMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}

			if (i != 0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
		}
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (right) {
			for (int r = lenthOfSnake - 1; r >= 0; r--) {
				snakeylength[r + 1] = snakeylength[r];
			}
			for (int r = lenthOfSnake; r >= 0; r--) {
				if (r == 0) {
					snakexlength[r] = snakexlength[r] + 25;
				} else {
					snakexlength[r] = snakexlength[r - 1];
				}
				if (snakexlength[r] > 850) {
					snakexlength[r] = 25;
				}

			}
			repaint();
		}
		if (left) {
			for (int r = lenthOfSnake - 1; r >= 0; r--) {
				snakeylength[r + 1] = snakeylength[r];
			}
			for (int r = lenthOfSnake; r >= 0; r--) {
				if (r == 0) {
					snakexlength[r] = snakexlength[r] - 25;
				} else {
					snakexlength[r] = snakexlength[r - 1];
				}
				if (snakexlength[r] < 25) {
					snakexlength[r] = 850;
				}

			}
			repaint();

		}
		if (up) {
			for (int r = lenthOfSnake - 1; r >= 0; r--) {
				snakexlength[r + 1] = snakexlength[r];
			}
			for (int r = lenthOfSnake; r >= 0; r--) {
				if (r == 0) {
					snakeylength[r] = snakeylength[r] - 25;
				} else {
					snakeylength[r] = snakeylength[r - 1];
				}
				if (snakeylength[r] < 75) {
					snakeylength[r] = 625;
				}

			}
			repaint();

		}
		if (down) {
			for (int r = lenthOfSnake - 1; r >= 0; r--) {
				snakexlength[r + 1] = snakexlength[r];
			}
			for (int r = lenthOfSnake; r >= 0; r--) {
				if (r == 0) {
					snakeylength[r] = snakeylength[r] + 25;
				} else {
					snakeylength[r] = snakeylength[r - 1];
				}
				if (snakeylength[r] > 625) {
					snakeylength[r] = 75;
				}

			}
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent k) {

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

}
