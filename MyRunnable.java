import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JFrame;

/**
 * This class implements the Runnable interface and is used to handle each client connection in a separate thread.
 * It contains a Socket, BufferedReader, BufferedWriter, Dictionary object and a counter for each client.
 * The class is responsible for loading the dictionary, displaying the GUI and handling client commands such as "d" for dictionary access
 * and "exit" for client disconnection.
 * 
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */

public class MyRunnable implements Runnable {
	
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String dictFile;
	private Dictionary dict;
	private int counter;
				
	/**
	 * Constructor that initializes the socket, BufferedReader, BufferedWriter, Dictionary object, dictionary file name and client counter.
	 * @param socket - the Socket object for the client connection
	 * @param dict - the Dictionary object that contains the word definitions
	 * @param dictFile - the name of the dictionary file
	 * @param counter - the client counter for tracking the number of connected clients
	 */
	
	public MyRunnable(Socket socket, Dictionary dict, String dictFile, int counter) {
		try {
			this.dictFile = dictFile;
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.dict = dict;
			this.counter = counter;
			
		} catch (IOException e) {
			close(socket, bufferedReader, bufferedWriter);
		}
	}
	
	/**
	 * The run() method is called when the thread is started.
	 * It handles client commands such as "d" for dictionary access and "exit" for client disconnection.
	 */
	@Override
	public void run() {
		// Loop until the client disconnects
		while (socket.isConnected()) {			
			try {				
				String clientCommand = bufferedReader.readLine();
				
				if (clientCommand != null && clientCommand.equalsIgnoreCase("d")) {
					// If the client's command is "d", it loads the dictionary from the specified file and creates a new instance of the MainFrame class to display the dictionary GUI.
					System.out.println("Client " + counter + " has accessed to the Dictonary!");
					dict.load(dictFile);
					MainFrame frame = new MainFrame(dict);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					
				} else if (clientCommand != null && clientCommand.equalsIgnoreCase("exit")) {
					// If the client's command is "exit", it indicates that the client has disconnected from the server.
					System.out.println("Client " + counter + " has disconnected to the server!");					
				}
				
			} catch(IOException e) {
				// If an IOException is thrown, the method closes the socket and input/output streams and breaks the loop.
				close(socket, bufferedReader, bufferedWriter);
				break;
			}
		}			
	}
	
	/**
	 * Closes the given socket, BufferedReader, and BufferedWriter if they are not null.
	 * @param socket - the Socket object to close
	 * @param bufferReader - the BufferedReader object to close
	 * @param bufferWriter - the BufferedWriter object to close
	 */
	public void close(Socket socket, BufferedReader bufferReader, BufferedWriter bufferWriter) {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (socket != null) {
				socket.close();
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
