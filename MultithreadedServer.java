import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * A multithreaded server that listens for incoming connections and creates a new thread for each connection.
 * The server communicates with clients through a Dictionary object.
 * 
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */
public class MultithreadedServer {
	// A counter to keep track of the number of clients that have connected to the server.
	private static int counter = 0;

	public static void main(String[] args) {
		if (args.length != 2) {
			// If the user does not enter exactly two arguments (port number and dictionary file name), print usage message and exit.
			System.err.println("Usage: java -jar DictionaryServer.jar <port> <dictionary-file>");
			System.exit(1);
		}
			
		try {
			// Create a server with the specified port number.
			ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
			// Get the dictionary file name from the second argument.
			String dictFile = args[1];

			// Create a Dictionary object from the dictionary file.
			Dictionary dict = new Dictionary(args[1]);
			
			// Enable socket reuse. 
			server.setReuseAddress(true);
			System.out.println("Waiting for client connection-");

			// Wait for incoming connections.
			while (true) {
				Socket socket = server.accept();
				counter++;
				System.out.println("Client " + counter + " has connected to the server!");
	
				// Start a new thread to handle the connection.
				MyRunnable myRunnable = new MyRunnable(socket, dict, dictFile, counter);
				Thread t = new Thread(myRunnable);
				t.start();
			}

		} catch (SocketException e) {
			// Handle socket exceptions.
			e.printStackTrace();
			
		} catch (IOException e) {
			// Handle I/O exceptions.
			e.printStackTrace();
			
		} catch (NumberFormatException e) {
			// Handle invalid port number exception.
            System.err.println("Invalid port number: " + args[0]);
            System.exit(1);
			
		} catch (IllegalArgumentException e) {
			// Handle the IllegalArgumentException if the port number is out of range.
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
