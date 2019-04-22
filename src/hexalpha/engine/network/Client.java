package hexalpha.engine.network;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

/**
* Managing connection between player and server.
*/
public class Client {

  public static final int port = 9898;
  private String hostname;
  private Socket client;
  private PrintWriter out;
  private BufferedReader in;

  /**
  * Creates a new client for hosting games
  *
  * @throws UnknownHostException
  * @throws ConnectException
  * @throws IOException
  */
  public Client(String hostname) throws UnknownHostException, ConnectException, IOException {

    client = new Socket(hostname, port);
    out = new PrintWriter(client.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
  }

  /**
  * Initialises the beginning of a game
  */
  public void beginGame() {
    send("hello");
    if (recieve().equals("hello")) {
      send("newgame");
    }
  }

  /**
  * Recieves input from server.
  *
  * @return the message recieved from the client
  */
  public String recieve() {
    String message = "failure";
    try {
      message = in.readLine();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return message;
  }

  public boolean isConnected() {
    return client.isConnected();
  }

  /**
  * Sends output to the server
  *
  * @param message the message being sent to the client.
  */
  public void send(String message) {
      out.println(message);
  }

  /**
  * Closes the connection.
  */
  public void close() throws IOException {
    client.close();
  }
 }
