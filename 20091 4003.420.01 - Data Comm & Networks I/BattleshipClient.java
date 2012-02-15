import java.net.InetSocketAddress;
import java.net.Socket;

/*
 *	Client portion of the Battleship program. Sets up the model, view and
 *	controllers for the client half of the application. BattleshipServer
 *	needs to be running before starting any BattleshipClients.
 *
 *	@author	Gabriel Smith (ges7506@rit.edu)
 *	@author Timothy Ransome (twr9948@rit.edu)
 */
public class BattleshipClient {

	/*
	 * Main method initializes the GUI, Model and Listeners
	 *
	 *	@param	arg[0]	address of server
	 *	@param	arg[1]	port of server
	 */
	public static void main (String[] args) throws Exception {
		if (args.length != 2) usage();
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		Socket socket = new Socket();
		socket.connect(new InetSocketAddress (host, port));

		final ServerProxy proxy = new ServerProxy(socket);
		final BattleshipUI ui = new BattleshipUI();
		final BattleshipModel model = new BattleshipModel();
		proxy.setListener(new ModelListener() {
			public void processAttack(int x, int y) {
				model.processAttack(x,y);
			}
			public void processResult(boolean hit, int lastX, int lastY) {
				model.processResult(hit, lastX, lastY);
			}
			public void updateGUI(char[][] myBoard, char[][] enemyBoard) {
				ui.updateGUI(myBoard, enemyBoard);
			}
			public void setTurn(boolean myTurn) {
				ui.setTurn(myTurn);
			}
			public void opponentQuit() {
				ui.opponentQuit();
			}
		});
		proxy.start();
		model.setListener(proxy);
		ui.addListener(proxy);
	}

	/*
	 * Prints a usage message to System.err
	 */
	private static void usage() {
		System.err.println("Usage: java BattleshipClient <host> <port>");
		System.err.println("<host> = Server host name");
		System.err.println("<port> = Server port number");
		System.exit (1);
	}
}
