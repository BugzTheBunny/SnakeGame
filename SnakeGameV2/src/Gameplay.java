
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	private int appleYpos[] = { 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };
	private int appleXpos[] = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private ImageIcon apple;
	private Random random = new Random();
	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(22);
	private int moves = 0;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int lenthOfSnake = 3;
	private ImageIcon rightMount, downMount, upMount, leftMount, snakeImage, titleImage;
	public int delay = 120;
	public int speed = 1;
	private Timer timer;
	private boolean gameOver = false;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	private int score = 0;

	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	Timer showDelay;

	public void scorePaint(Graphics graphics) {

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 14));
		graphics.drawString("{Great!} +1", appleXpos[xPos], appleYpos[yPos] - 20);

		graphics.dispose();
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
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawRect(25, 9, 850, 55);
		graphics.fillRect(0, 0, 900, 900);
		// Draw title image.
		titleImage = new ImageIcon("assets/snaketitle.jpg");
		titleImage.paintIcon(this, graphics, 25, 10);
		// Draw game borders.
		graphics.setColor(Color.WHITE);
		graphics.drawRect(23, 73, 853, 578);
		// Draw background for the gameplay.
		graphics.setColor(Color.BLACK);
		graphics.fillRect(25, 75, 850, 575);
		graphics.drawString("github.com/BugzTheBunny/SnakeGame", 325, 665);

		rightMount = new ImageIcon("assets/rightmouth.png");
		rightMount.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
		// Draw the Score
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 14));
		graphics.drawString("Score: " + score, 780, 30);
		// Draw the Points
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 14));
		graphics.drawString("Points: " + (lenthOfSnake - 3), 780, 45);
		// Draw the Speed
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 14));
		graphics.drawString("Speed: " + speed, 780, 60);

		for (int i = 0; i < lenthOfSnake; i++) {
			if (i == 0 && right) {
				rightMount = new ImageIcon("assets/rightmouth.png");
				rightMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && left) {
				leftMount = new ImageIcon("assets/leftmouth.png");
				leftMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && down) {
				downMount = new ImageIcon("assets/downmouth.png");
				downMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
			if (i == 0 && up) {
				upMount = new ImageIcon("assets/upmouth.png");
				upMount.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}

			if (i != 0) {
				snakeImage = new ImageIcon("assets/snakeimage.png");
				snakeImage.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
			}
		}

		apple = new ImageIcon("assets/enemy.png");
		if ((appleXpos[xPos] == snakexlength[0]) && (appleYpos[yPos] == snakeylength[0])) {
			scorePaint(graphics);
			lenthOfSnake++;
			score++;
			timer.stop();
			delay = delay - 2;
			timer = new Timer(delay, this);
			speed++;
			timer.start();
			xPos = random.nextInt(34);
			yPos = random.nextInt(22);
		}
		apple.paintIcon(this, graphics, appleXpos[xPos], appleYpos[yPos]);

		for (int b = 1; b < lenthOfSnake; b++) {
			if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
				gameOver = true;
				right = false;
				left = false;
				up = false;
				down = false;
				graphics.setColor(Color.WHITE);
				graphics.setFont(new Font("TimesRoman", Font.BOLD, 45));
				graphics.drawString("Score: " + score, 350, 350);
				graphics.setColor(Color.WHITE);
				graphics.setFont(new Font("TimesRoman", Font.BOLD, 45));
				graphics.drawString("Press any key to restart", 270, 300);
				graphics.setColor(Color.RED);
				graphics.setFont(new Font("TimesRoman", Font.BOLD, 45));
				graphics.drawString("GAME OVER", 320, 250);
				moves = 0;
				score = 0;
				timer.stop();
				delay = 120;
				timer = new Timer(delay, this);

				speed = 0;
				timer.start();
				lenthOfSnake = 3;
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

		if (k.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lenthOfSnake = 3;
			gameOver = false;
			repaint();

		}
		if (k.getKeyCode() == KeyEvent.VK_RIGHT && gameOver == false) {
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
		if (k.getKeyCode() == KeyEvent.VK_LEFT && gameOver == false) {
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
		if (k.getKeyCode() == KeyEvent.VK_UP && gameOver == false) {
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
		if (k.getKeyCode() == KeyEvent.VK_DOWN && gameOver == false) {
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
	public void keyReleased(KeyEvent k) {

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
