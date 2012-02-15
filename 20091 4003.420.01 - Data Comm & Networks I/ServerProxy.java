import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/*
 *	Acts as a server proxy for the client portion of Battleship. Reads and
 *	handles any messages incoming on the socket and sends messages out to the
 *	opponent.
 *
 *	@author	Gabriel Smith (ges7506@rit.edu)
 *	@author Timothy Ransome (twr9948@rit.edu)
 */
public class ServerProxy implements UIListener {
	// Private data members
	private Socket socket;
	private DataOutputStream out;	// write to socket
	private DataInputStream in;		// read from socket
	private ModelListener listener;

	private int lastX;	// stores x coord from last attack
	private int lastY;	// stores y coord from last attack

	/*
	 *	Constructor - sets up input and output streams
	 *
	 *	@param	socket	socket to read from and write to
	 */
	public ServerProxy(Socket socket) throws IOException {
		this.socket = socket;
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
	}

	/*
	 *	Sets the listener to be used
	 *
	 *	@param	listener	listener object to set
	 */
	public void setListener (ModelListener listener) {
		if (listener == null) {
			throw new NullPointerException
				("ServerProxy.setListener(): listener is null");
		}
		this.listener = listener;
	}


	/**************************************
	* Functions inherited from UIListener *
	**************************************/

	/*
	 *	Sends an attack out over the socket. Uses opcode 'a'
	 *
	 *	@param	x	x coordinate of attack
	 *	@param	y	y coordinate of attack
	 */
	public void sendAttack(int x, int y) throws IOException {
		lastX = x;
		lastY = y;
		out.writeChar('a'); //Attack Opcode 'a'
		out.writeInt(x);
		out.writeInt(y);
		out.flush();
	}

	/*
	 *	Sends the result of opponent's last attack over the socket.
	 *	Uses opcode 'r'
	 *
	 *	@param hit	whether last attack hit or not
	 */
	public void sendResult(boolean hit) throws IOException {
		out.writeChar('r'); //Result Opcode 'r'
		out.writeBoolean(hit);
		out.flush();
	}

	/*
	 *	Tells the GUI to update the display with new board configurations
	 *
	 *	@param	myBoard		configuration of my game board
	 *	@param	enemyBoard	configuration of enemy game board
	 */
	public void updateGUI(char[][] myBoard, char[][] enemyBoard) {
		listener.updateGUI(myBoard, enemyBoard);
	}

	/*
	 *	Tells GUI whether or not it's my turn.
	 *
	 *	@param	myTurn	True if it's my turn to attack
	 */
	public void setTurn(boolean myTurn) {
		listener.setTurn(myTurn);
	}

	/*
	 *	Tells the GUI that the opponent has quit, ending the game.
	 */
	public void opponentQuit() {
		listener.opponentQuit();
	}

	/*
	 *	Tells the GUI that you have lost the game.
	 */
	public void endGame() {
		stop();
	}


	/*******************
	* Socket functions *
	*******************/

	/*
	 *	Begins the Reader thread to accept messages from socket.
	 */
	public void start() {
		new Reader().start();
	}

	/*
	 *	Closes the socket to end the game.
	 */
	public void stop() {
		try { socket.shutdownInput(); } catch (IOException exc) {}
	}

	/*
	 *	Reads messages from the socket. Uses opcodes to determine
	 *	the type of messages.
	 *	Opcodes:
	 *		'a' -	Receiving an attack
	 *		'r' - 	Receiving the results of last attack
	 */
	private class Reader extends Thread {
		public void run() {
			try {
				for (;;) {
					char code = in.readChar();
					switch (code) {
						case ('a'):	// attack
							int x = in.readInt();
							int y = in.readInt();
							listener.processAttack(x,y);
							break;
						case ('r'):	// results
							boolean hit=in.readBoolean();
							listener.processResult(hit, lastX, lastY);
							break;
						default:
							System.err.println("Reader received bad code.");
							System.exit(1);
							break;
					}


				}
			} catch (EOFException e) {
				listener.opponentQuit();
			} catch (IOException e) {
				System.err.println ("ServerProxy.Reader.run(): I/O error");
				e.printStackTrace (System.err);
			} finally {
				try { socket.close(); }
				catch (IOException e) {e.printStackTrace(System.err);}
			}
		}
	}

}
