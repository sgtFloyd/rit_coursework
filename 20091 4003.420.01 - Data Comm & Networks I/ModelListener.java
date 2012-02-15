import java.io.IOException;

/*
 *	Inferface for the client-side model controller of the Battleship program.
 *
 *	@author Timothy Ransome (twr9948@rit.edu)
 *	@author	Gabriel Smith (ges7506@rit.edu)
 */
public interface ModelListener {

	/*
	 *	Takes an x and y coordinate and checks if there is a ship
	 *	at that location. Sends the result of the attack back to
	 *	the opponent.
	 *
	 *	@param	x	x coordinate of position to check
	 *	@param	y	y coordinate of position to check
	 */
	public void processAttack(int x, int y) throws IOException;
	
	/*
	 *	Takes the result of the last attack and updates the board
	 *
	 *	@param	hit		whether previous attack was a hit or miss
	 *	@param	lastX	x coordinate of last attack
	 *	@param	lastY	y coordinate of last attack
	 */
	public void processResult(boolean hit, int lastX, int lastY)
		throws IOException;

	/*
	 *	Sends the updated game boards to the GUI to be displayed
	 *
	 *	@param	myBoard		configuration of my game board
	 *	@param	enemyBoard	known configuration of enemy game board
	 */
	public void updateGUI(char[][] myBoard, char[][] enemyBoard);

	/*
	 *	Tells the GUI whether or not it's my turn, allowing the
	 *	user to attack or not to attack.
	 *
	 *	@param	myTurn	True if it is currently my turn to attack
	 */
	public void setTurn(boolean myTurn);

	/*
	 *	Called when the socket is closed unexpectedly, telling
	 *	us that the opponent has quit the game.
	 */
	public void opponentQuit();
}
