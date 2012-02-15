import java.io.IOException;

/*
 *	Inferface for the server-side controller of the Battleship program.
 *
 *	@author Timothy Ransome (twr9948@rit.edu)
 *	@author	Gabriel Smith (ges7506@rit.edu)
 */
public interface ServerListener {

	/*
	 *	Sends a message to client attacking a position on the board.
	 *
	 *	@param	x	x coordinate of square to attack
	 *	@param	y	y coordinate of square to attack
	 */
	public void attackSquare(int x, int y) throws IOException;

	/*
	 *	Sends the results of an attack back to the attacker.
	 *
	 *	@param	result	results from the attack. True if hit
	 */
	public void sendResult(boolean result) throws IOException;

}
