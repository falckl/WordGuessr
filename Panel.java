package wordGuesserGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
	
private static final long serialVersionUID = 1L;
	
	static final int screenWidth = 500;
	static final int screenHeight = 500;
	static final int unitSize = 100;
	
	static final int gameUnits = ((screenWidth*screenHeight)/unitSize);
	
	boolean running = false;
	Random random;
	private String letter1 = "";
	private String letter2 = "";
	private String letter3 = "";
	private String letter4 = "";
	private String letter5 = "";
	private String[][] letters = {{"","","","",""}, {"","","","",""},
			{"","","","",""}, {"","","","",""},{"","","","",""}};
	
	private int[][] grid = {{0,0,0,0,0}, {0,0,0,0,0},
			{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
	
	private int counter = 0;
	private int numGuesses = 0;
	private boolean readyToCheck = false;
	private String theWord = "";
	private boolean guessed = false;
	
	Timer timer;
	static final int delay = 0;
	
	
	Panel() {
		random = new Random();
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new Typing());
		startGame();
	}
	
	public void startGame() {
		getWord();
		//System.out.println(theWord);
		running = true;
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public String getWord() {
		Word set = new Word();
		theWord = set.getRandomWord();
		return theWord;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (running) {
			//turn into a grid
			for (int i = 0; i < screenHeight/unitSize; i++) {
				g.setColor(Color.gray);
				g.drawLine(i*unitSize, 0, i*unitSize, screenHeight);
				g.drawLine(0, i*unitSize, screenWidth, i*unitSize);
			}
			//draw colours onto grid
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid.length; col++) {
					if (grid[row][col] == 1) {
						g.setColor(Color.yellow);
						g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
					} else if (grid[row][col] == 2) {
						g.setColor(Color.green);
						g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
					}
				}
			}
			//draw each letter on the board
			for (int xAxis = 0; xAxis < letters.length; xAxis++) {
				for (int yAxis = 0; yAxis < letters.length; yAxis++) {
					g.setColor(Color.white);
					g.setFont(new Font("Courier", Font.BOLD, 70));
					FontMetrics metrics = getFontMetrics(g.getFont());
					int x = ((100+(xAxis*200)) - metrics.stringWidth(letters[yAxis][xAxis])) / 2;
					int y = (metrics.getAscent() + ((100+(yAxis*200)) - (metrics.getAscent() + metrics.getDescent())) / 2);
					g.drawString(letters[yAxis][xAxis], x, y);
				}
			}
			
		} else {
			for (int i = 0; i < screenHeight/unitSize; i++) {
				g.setColor(Color.gray);
				
			}
			if (guessed == true) {
				g.setColor(Color.blue);
//				g.fillRect(100, 0, unitSize, unitSize);
//				g.fillRect(0,100,unitSize,unitSize);
//				g.fillRect(300, 0, unitSize, unitSize);
//				g.fillRect(0, 300, unitSize, unitSize);
//				g.fillRect(100, 200, unitSize, unitSize);
//				g.fillRect(200, 100, unitSize, unitSize);
//				g.fillRect(100, 400, unitSize, unitSize);
//				g.fillRect(200, 300, unitSize, unitSize);
//				g.fillRect(300, 200, unitSize, unitSize);
//				g.fillRect(300, 400, unitSize, unitSize);
//				g.fillRect(400, 300, unitSize, unitSize);
//				g.fillRect(400, 100, unitSize, unitSize);
				g.fillRect(0, 0, screenWidth, screenHeight);
				
				g.setColor(Color.white);
				g.setFont(new Font("Courier", Font.BOLD, 70));
				FontMetrics metrics = getFontMetrics(g.getFont());
				int x = (screenWidth - metrics.stringWidth("NICE")) / 2;
				g.drawString("NICE", x, 275);
			} else {
				for (int i = 0; i < screenHeight/unitSize; i++) {
				g.setColor(Color.red);
				g.fillRect(0, 0, screenWidth, screenHeight);
				g.setColor(Color.black);
				g.setFont(new Font("Courier", Font.BOLD, 70));
				FontMetrics metrics = getFontMetrics(g.getFont());
				int x = (screenWidth - metrics.stringWidth("DUMBASS")) / 2;
				g.drawString("DUMBASS", x, 275);
				}
					
			}
			g.setColor(Color.white);
			g.setFont(new Font("The word was: " + theWord, Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			int x = (screenWidth - metrics.stringWidth("The word was: " + theWord)) / 2;
			int y = (metrics.getAscent() + (screenHeight+200 - (metrics.getAscent() + metrics.getDescent())) / 2);
			g.drawString("The word was: " + theWord, x, y);
			
		}
	}
	
	
	public void checkWord() {
		if (readyToCheck ==true) {
			int greens = 0;
			if ((String.valueOf((theWord).charAt(0))).equals(letter1)) {
				grid[numGuesses][0] = 2;
				greens++;
			} else if (theWord.contains(letter1)) {
				grid[numGuesses][0] = 1;
			}
			if ((String.valueOf((theWord).charAt(1))).equals(letter2)) {
				grid[numGuesses][1] = 2;
				greens++;
			} else if (theWord.contains(letter2)) {
				grid[numGuesses][1] = 1;
			}
			if ((String.valueOf((theWord).charAt(2))).equals(letter3)) {
				grid[numGuesses][2] = 2;
				greens++;
			} else if (theWord.contains(letter3)) {
				grid[numGuesses][2] = 1;
			}
			if ((String.valueOf((theWord).charAt(3))).equals(letter4)) {
				grid[numGuesses][3] = 2;
				greens++;
			} else if (theWord.contains(letter4)) {
				grid[numGuesses][3] = 1;
			}
			if ((String.valueOf((theWord).charAt(4))).equals(letter5)) {
				grid[numGuesses][4] = 2;
				greens++;
			} else if (theWord.contains(letter5)) {
				grid[numGuesses][4] = 1;
			}
			if (greens == 5) {
				guessed = true;
				running = false;
			}
			
			
			if (numGuesses == 4) {
				running = false;
			}
			//set checking back to false
			readyToCheck = false;
			
			numGuesses++;
			letter1 = "";
			letter2 = "";
			letter3 = "";
			letter4 = "";
			letter5 = "";
			printGrid();
		}
	}
	
	public void printGrid() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				System.out.println(grid[row][col]);
			}
		}
	}
	
	public void typing() {
		if (numGuesses < 5) {
			letters[numGuesses][0] = letter1;
			letters[numGuesses][1] = letter2;
			letters[numGuesses][2] = letter3;
			letters[numGuesses][3] = letter4;
			letters[numGuesses][4] = letter5;
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			
			typing();
			checkWord();
		}
		repaint();
	}
	
	public class Typing extends KeyAdapter implements KeyListener {
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (numGuesses < 5) {
				char keyChar;
				keyChar = e.getKeyChar();
				if (Character.isLetter(keyChar)) {
					String letter = String.valueOf(keyChar);
					letter = letter.toUpperCase();
					if (counter == 0) {
						letter1 = String.valueOf(letter);
						System.out.println(letter1);
						counter++;
					} else if (counter == 1) {
						letter2 = String.valueOf(letter);
						System.out.println(letter2);
						counter++;
					} else if (counter == 2) {
						letter3 = String.valueOf(letter);
						System.out.println(letter3);
						counter++;
					} else if (counter == 3) {
						letter4 = String.valueOf(letter);
						System.out.println(letter4);
						counter++;
					} else if (counter == 4) {
						letter5 = String.valueOf(letter);
						System.out.println(letter5);
						counter++;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (counter == 1) {
						letter1 = "";
						counter--;
					} else if (counter == 2) {
						letter2 = "";
						counter--;
					} else if (counter == 3) {
						letter3 = "";
						counter--;
					} else if (counter == 4) {
						letter4 = "";
						counter--;
					} else if (counter == 5) {
						letter5 = "";
						counter--;
					}
				} else if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (counter == 5)) {
					readyToCheck = true;
					counter = 0;
					
				}
			} 
		}
	}
	

}

