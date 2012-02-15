import java.io.IOException;

/*
 *	Inferface for the client-side UI controller of the Battleship program.
 *
 *	@author Timothy Ransome (twr9948@rit.edu)
 *	@author	Gabriel Smith (ges7506@rit.edu)
 */
public interface UIListener {

	/*
	 *	Sends an attack out over the socket. Uses opcode 'a'
	 *
	 *	@param	x	x coordinate of attack
	 *	@param	y	y coordinate of attack
	 */
	public void sendAttack(int x, int y) throws IOException;

	/*
	 *	Sends the result of opponent's last attack over the socket.
	 *	Uses opcode 'r'
	 *
	 *	@param hit	whether last attack hit or not
	 */
	public void sendResult(boolean hit) throws IOException;

	/*
	 *	Tells the GUI to update the display with new board configurations
	 *
	 *	@param	myBoard		configuration of my game board
	 *	@param	enemyBoard	configuration of enemy game board
	 */
	public void updateGUI(char[][] myBoard, char[][] enemyBoard);

	/*
	 *	Tells GUI whether or not it's my turn.
	 *
	 *	@param	myTurn	True if it's my turn to attack
	 */
	public void setTurn(boolean myTurn);

	/*
	 *	Tells the GUI that you have lost the game.
	 */
	public void endGame();

	/*
	 *	Tells the GUI that the opponent has quit, ending the game.
	 */
	public void opponentQuit();
}
