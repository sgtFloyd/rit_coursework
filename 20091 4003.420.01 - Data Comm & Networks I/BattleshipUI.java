import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.IOException;

/*
 *	The GUI for the client portion of the Battleship program. Builds and
 *	displays the interface and handles any input from the user. Also displays
 *	important messages from the model.
 *
 *	@author Tim Ransome (twr9948@rit.edu)
 *	@author	Gabriel Smith (ges7506@rit.edu)
 */
public class BattleshipUI {
	private List<UIListener> listeners = new ArrayList<UIListener>();
	private JFrame frame;
	private JButton[][] bottomBoard;	// buttons to represent my board
	private JButton[][] topBoard;		// buttons to represent opponent's board
	private Boolean gameOver = false;
	private Boolean myturn = true;		// True if it's my turn to attack

	// Constants for defining board color scheme
	private	final Color _WATER = Color.BLUE;
	private	final Color _SHIP = Color.BLACK;
	private	final Color _HIT = Color.RED;
	private	final Color _MISS = Color.LIGHT_GRAY;

	/*
	 *	Constructor - Builds and displays the GUI
	 */
	public BattleshipUI() {
		bottomBoard = new JButton[10][10];
		topBoard = new JButton[10][10];
		frame = new JFrame("Battleship");

		GridLayout mainLayout = new GridLayout(2,1);
		mainLayout.setVgap(20);
		JPanel panel = new JPanel(mainLayout);
		JPanel topPanel = new JPanel(new GridLayout(10,10));
		JPanel bottomPanel = new JPanel(new GridLayout(10,10));
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				JButton button = new JButton();
				bottomPanel.add(button);
				bottomBoard[i][j] = button;
			}
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				JButton button = new JButton();
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 10; i++)
							for (int j = 0; j < 10; j++) {
								if (e.getSource() == topBoard[i][j]) {
									if ((topBoard[i][j].getBackground() == _WATER) && myturn)
										attackSquare(i, j);
								}
							}
					}
				});
				topPanel.add(button);
				topBoard[i][j] = button;
			}

		panel.add(topPanel);
		panel.add(bottomPanel);

		frame.add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				for(UIListener listener : listeners) {
					listener.endGame();
				}
				System.exit (0);
			}
		});
		frame.setMinimumSize(new Dimension(300, 500));
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 *	If the socket closes unexpectedly, display a message saying the opponent has quit the game.
	 */
	public void opponentQuit() {
		if (!gameOver) {
			frame.setTitle("Battleship - Opponent has forfeit the game!");
			JOptionPane.showMessageDialog(null,"Your opponent has forfeit the game! You Win!",
											"Game Over", JOptionPane.DEFAULT_OPTION);
			gameOver = true;
		}
	}

	/*
	 *	Sets the title message displaying whose turn it is to attack
	 */
	public void setTurn(boolean turn) {
		if (!gameOver) {
			if (turn)
				frame.setTitle("Battleship - Your Turn!");
			else 
				frame.setTitle("Battleship - Opponent's Turn!");
			myturn = turn;
		}
	}

	/*
	 *	Updates the display with new boards and checks for a win or lose
	 *
	 *	@param	myboard		configuration of my board
	 *	@param	enemyboard	configuration of opponent's board
	 */
	public void updateGUI(char[][] myboard, char[][] enemyboard) {
		int count = 0;
		int enemyCount = 0;
		// iterate over every button, setting it to the proper color
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				bottomBoard[i][j].setEnabled(false);
				topBoard[i][j].setEnabled(false);
				switch (myboard[i][j]) {
					case 'O':
						count++;
						bottomBoard[i][j].setBackground(_SHIP);
						break;
					case 'X':
						bottomBoard[i][j].setBackground(_HIT);
						break;
					case '.':
						bottomBoard[i][j].setBackground(_WATER);
						break;
					case '+':
						bottomBoard[i][j].setBackground(_MISS);
						break;
				}
				switch (enemyboard[i][j]) {
					case 'O':
						topBoard[i][j].setBackground(_SHIP);
						break;
					case 'X':
						enemyCount++;
						topBoard[i][j].setBackground(_HIT);
						break;
					case '.':
						topBoard[i][j].setBackground(_WATER);
						topBoard[i][j].setEnabled(true);
						break;
					case '+':
						topBoard[i][j].setBackground(_MISS);
						break;
				}
			}
		// Check if you've run out of ships
		// You lose when your ship count reaches 0
		if (count == 0) {
			if (!gameOver) {
				gameOver = true;
				frame.setTitle("Battleship - You Lose!");
				JOptionPane.showMessageDialog(null, "You Lose!", "Game Over", JOptionPane.DEFAULT_OPTION);
				for(UIListener listener : listeners) {
					listener.endGame();
				}
			}	
		}
		// Check if you've found all the enemy ships
		// You win when you've hit 17 ships
		if (enemyCount == 17) {
			if (!gameOver) {
				gameOver = true;
				frame.setTitle("Battleship - You Win!");
				JOptionPane.showMessageDialog(null, "You Win!", "Game Over", JOptionPane.DEFAULT_OPTION);
			}
		}
	}

	/*
	 *	Adds a listener to the List of UIListeners.
	 *
	 *	@param	listener	listener to add
	 */
	public synchronized void addListener(UIListener listener) {
		listeners.add(listener);
	}
	
	/*
	 *	Sends an message to each UIListener telling it to send an attack message.
	 *	Sets the current turn to opponent's
	 *
	 *	@param	x	x coordinate of position to attack
	 *	@param	y	y coordinate of position to attack
	 */
	private synchronized void attackSquare(int x, int y) {
		setTurn(false);
		try {
			for(UIListener listener : listeners) {
				listener.sendAttack(x,y);
			}
		} catch (IOException e) { e.printStackTrace(System.err); }
	}
}
