import java.io.IOException;
import java.util.Random;

/*
 *	The backend for the client portion of the Battleship program. Keeps track
 *	of game boards and handles any operations on them.
 *
 *	@author	Gabriel Smith (ges7506@rit.edu)
 *	@author Tim Ransome (twr9948@rit.edu)
 */
public class BattleshipModel implements ModelListener {
	private UIListener listener;

	// Storage for board states
	private char[][] myBoard;		// complete state of my board
	private char[][] enemyBoard;	// known state of enemy board
	
	// Board Constants
	private final int BOARD_W = 10;    // board width
	private final int BOARD_H = 10;    // board height
	private final int[] ships = {5, 4, 3, 3, 2}; // ship lengths
	private final char _SHIP = 'O';    // unhit ship
	private final char _HIT = 'X';     // hit ship
	private final char _WATER = '.';   // unhit water
	private final char _SPLASH = '+';  // hit water

	/*
	 *	Constructor, populates game boards
	 */
	public BattleshipModel() {
		myBoard = new char[BOARD_W][BOARD_H];
		enemyBoard = new char[BOARD_W][BOARD_H];
		// initially fill boards with empty water
		for (int x=0; x<BOARD_W; x++) {
			for (int y=0; y<BOARD_H; y++) {
				enemyBoard[x][y] = _WATER;
				myBoard[x][y] = _WATER;
			}
		}
		// fill myBoard with ships placed randomly
		generateRandomBoard();
	}

	/*
	 *	Sets the listener to be used for this model
	 *
	 *	@param	listener	listener object to set
	 */
	public void setListener (UIListener listener) {
		if (listener == null) {
			throw new NullPointerException
				("BattleshipModel.setListener(): listener is null");
		}
		this.listener = listener;
		updateGUI(myBoard, enemyBoard);
	}


	/************************************
	* Functions called from ServerProxy *
	************************************/

	/*
	 *	Takes an x and y coordinate and checks if there is a ship
	 *	at that location. Sends the result of the attack back to
	 *	the opponent.
	 *
	 *	@param	x	x coordinate of position to check
	 *	@param	y	y coordinate of position to check
	 */
	public void processAttack(int x, int y) {
		boolean hit = false;
		// check myBoard for hit at x,y
		// update board with results
		if (myBoard[x][y] == _SHIP) {
			myBoard[x][y] = _HIT;
			hit = true;
		} else {
			myBoard[x][y] = _SPLASH;
		}
		try { listener.sendResult(hit); }
		catch (IOException e) { e.printStackTrace(System.err); }
		updateGUI(myBoard, enemyBoard);
		setTurn(true);
	}

	/*
	 *	Takes the result of the last attack and updates the board
	 *
	 *	@param	hit		whether previous attack was a hit or miss
	 *	@param	x		x coordinate of last attack
	 *	@param	y		y coordinate of last attack
	 */
	public void processResult(boolean hit, int x, int y) {
		if (hit) {
			enemyBoard[x][y] = _HIT;
		} else {
			enemyBoard[x][y] = _SPLASH;
		}
		updateGUI(myBoard, enemyBoard);
	}

	/*
	 *	Sends the updated game boards to the GUI to be displayed
	 *
	 *	@param	myBoard		configuration of my game board
	 *	@param	enemyBoard	known configuration of enemy game board
	 */
	public void updateGUI(char[][] myBoard, char[][] enemyBoard) {
		listener.updateGUI(myBoard, enemyBoard);
	}

	/*
	 *	Tells the GUI whether or not it's my turn, allowing the
	 *	user to attack or not to attack.
	 *
	 *	@param	myTurn	True if it is currently my turn to attack
	 */
	public void setTurn(boolean myTurn) {
		listener.setTurn(myTurn);
	}

	/*
	 *	Called when the socket is closed unexpectedly, telling
	 *	us that the opponent has quit the game.
	 */
	public void opponentQuit() {
		listener.opponentQuit();
	}


	/*************************************
	* Private board management functions *
	*************************************/

	/*
	 *	Populates the board with the proper ships at random locations
	 *	and facing random directions.
	 */
	private void generateRandomBoard() {
		Random rand = new Random();
		for (int i=0; i<ships.length; i++) {
			int shipLength = ships[i];
			int xZero;
			int yZero;
			boolean vertical;
			do {
				xZero = rand.nextInt(BOARD_W);
				yZero = rand.nextInt(BOARD_H);
				vertical = rand.nextBoolean();
			} while ( !setShip(shipLength, xZero, yZero, vertical) );
		}
	}

	/*
	 *	Atttempts to place a ship in a certain position.
	 *
	 *	@param	shiplength	Length of ship to be set
	 *	@param	xZero		Starting x position
	 *	@param	yZero		Starting y position
	 *	@param	vertical	True if ship is vertical, false if horizontal
	 */
	private boolean setShip(int shipLength, int xZero,
							int yZero, boolean vertical) {
		int x = xZero;
		int y = yZero;

		// check if placement is out of bounds
		if ( vertical && ((yZero+shipLength)>(BOARD_H-1)) ) {
			return false;
		} else if (!vertical && ((xZero+shipLength)>(BOARD_W-1)) ) {
			return false;
		}
		// check if placement overlaps another ship
		for (int i=0; i<shipLength; i++) {
			if (myBoard[x][y] != _WATER) {
				return false;
			}
			if (vertical) {
				y++;
			} else {
				x++;
			}
		}

		// place ship
		x = xZero;
		y = yZero;
		for (int i=0; i<shipLength; i++) {
			myBoard[x][y] = _SHIP;
			if (vertical) {
				y++;
			} else {
				x++;
			}
		}
		// ship successfully placed
		return true;
	}

	/*
	 *	Test function to print the state of the board to System.out
	 *
	 *	@param board	configuration to print
	 */
	private void printBoard(char[][] board) {
		System.out.println();
		for (int i=0; i<BOARD_W; i++) {
			for (int j=0; j<BOARD_H; j++) {
				System.out.print(board[j][i] + " ");
			}
			System.out.println("");
		}
	}

}
