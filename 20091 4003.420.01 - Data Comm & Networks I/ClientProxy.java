import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientProxy extends Thread implements ServerListener {

	private DataInputStream input;
	private DataOutputStream output;
	private ServerListener listener;
	private Socket socket;

	public ClientProxy(Socket socket) {
		this.socket = socket;
		try {
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error creating input and output streams");
		}
	}

	public void setListener(ServerListener listener) {
		this.listener = listener;
		start();
	}

	public void attackSquare(int x, int y) throws IOException {
		// my partner wants to attack my square at (x,y)
		output.writeChar('a');
		output.writeInt(x);
		output.writeInt(y);
		output.flush();
	}

	public void sendResult(boolean result) throws IOException {
		output.writeChar('r');
		output.writeBoolean(result);
		output.flush();
	}

	public void run() {
		try {
			for (;;) {
			// when you recieve an attack command, call attackSquare
			// your partner executes attackSquare, and calls sendResult on us
				char opcode = input.readChar();
				switch (opcode) {
					case 'a':
						int x = input.readInt();
						int y = input.readInt();
						listener.attackSquare(x, y);
						break;
					case 'r':
						boolean r = input.readBoolean();
						listener.sendResult(r);
						break;
				}
			}
		} catch (EOFException e) {
		} catch (IOException e) {
		} finally { try { socket.close(); }
					catch (IOException e) { e.printStackTrace(System.err); }}
	}
}
