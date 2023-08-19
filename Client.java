import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class represents a client for the dictionary server. It connects to the server
 * and allows the user to send requests to the server by typing commands into the console.
 * The class uses a socket to communicate with the server.
 * 
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */

public class Client {
	
	// The buffered writer used to send messages to the server.
	private BufferedWriter bufferedWriter;
	
	// The buffered reader used to read messages from the server.
	private BufferedReader bufferedReader;
	
	// The socket used to connect to the server.
	private Socket socket;
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			// If the user does not enter exactly two arguments (hostname and port number), print usage message and exit.
            System.err.println("Usage: java -jar DictionaryClient.jar <hostname> <port>");
            System.exit(1);
        }
		
		try {
			// Create a new socket object and connect to the server.
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            
            // Create a new instance of the client class and call sendMessage() to start sending messages to the server.
            Client client = new Client(socket);         
            client.sendMessage();
            
        } catch (NumberFormatException e) {
        	// Handle the NumberFormatException if the port number is invalid.
            System.err.println("Invalid port number: " + args[1]);
            System.exit(1);
        } catch (UnknownHostException e) {
        	// Handle the UnknownHostException if the specified host is not found.
            System.err.println("Unknown host: " + args[0]);
            System.exit(1);
        } catch (SocketException e) {
        	// Handle the SocketException if there is an error with the socket.
            System.err.println("Socket error: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
        	// Handle the IllegalArgumentException if the port number is out of range.
            System.err.println("Error: Port number is out of range");
        } catch (IOException e) {
        	// Handle the IOException if there is an error with the input or output.
            System.err.println("I/O error: " + e.getMessage());
            System.exit(1);
        }
	}
	
	/**
	 * Constructor for the Client class. Initializes the instance variables with the socket, input stream reader, 
	 * output stream writer, and socket passed in as parameters.
	 * @param socket - The socket object used for the client-server communication
	 */
	public Client(Socket socket) {
		
		try {
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.socket = socket;
		
		} catch (IOException e) {
			close(socket, bufferedReader, bufferedWriter);
		}
	}
	
	/**
	 * Sends messages to the server.
	 */
	public void sendMessage() {
		try {
			// Create a new scanner object to read input from the console.
			Scanner scanner = new Scanner(System.in);
			String userInput = "";
			
			// Loop until the user types "exit".
			while(!(userInput.equalsIgnoreCase("exit"))){
				System.out.println("Please type \"d\" to access the dictionary server or type \"exit\" to leave: ");
				// Read input from the console
				userInput = scanner.nextLine();
				// Send the input to the server
				bufferedWriter.write(userInput);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
			// closes the scanner object after the loop exits.
			scanner.close();
			close(socket, bufferedReader, bufferedWriter);
		} catch (IOException e) {
			close(socket, bufferedReader, bufferedWriter);
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
	
	
		
