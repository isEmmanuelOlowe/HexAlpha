package hexalpha.engine.network;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

/**
* Manging connection between player and client.
*/
public class Server {

  public static final int port = 9898;
  private String hostname;
  private ServerSocket server;
  private Socket client;
  private PrintWriter out;
  private BufferedReader in;

  /**
  * Creates a new server for hosting games
  *
  * @throws UnknownHostException
  * @throws ConnectException
  * @throws IOException
  */
  public Server() throws UnknownHostException, ConnectException, IOException {
    hostname = InetAddress.getLocalHost().getHostAddress();
    server = new ServerSocket(port);
  }

  /**
  * Prepares server for connecting to client
  *
  * @throws IOException in the event no connection can be made
  */
  public void connect() throws IOException {
    client = server.accept();
    out = new PrintWriter(client.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
  }

  /**
  * Initialises the beginning of a game
  */
  public void beginGame() {
    if (recieve().equals("hello")) {
      send("hello");
      if (recieve().equals("newgame")) {
      }
    }
  }

  /**
  * Gets the IP address of the current system.
  */
  public String getIP () {
    return hostname;
  }

  /**
  * Determines if the server is connected to a client.
  *
  * @return true if connected to a client.
  */
  public boolean isConnected() {
    return client.isConnected();
  }

  /**
  * Recieves input from client.
  *
  * @return the message recieved from the client
  */
  public String recieve()  {
    String message = "quit";
    try {
      message = in.readLine();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Client says: " + message);
    return message;
  }

  /**
  * Sends output to the client
  *
  * @param message the message being sent to the client.
  */
  public void send(String message) {
      out.println(message);
  }

  /**
  * Closes the connection.
  */
  public void close() {
    try {
      server.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
 }
