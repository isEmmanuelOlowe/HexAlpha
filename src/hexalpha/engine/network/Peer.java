package hexalpha.engine.network;

import hexalpha.engine.NetworkGame;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
* Recieves input from other socket via multithreading.
*/
public class Peer extends Thread {

  private Server server;
  private Client client;
  private NetworkGame game;

  /**
  * Creates a new thread to recieve messages
  *
  * @param server the server that has the connection
  * @param game the that is currently taking place
  *
  */
  public Peer(Server server, NetworkGame game) {
    this.server = server;
    this.game = game;
  }

  /**
  * Creates a new thread to recieve messages
  *
  * @param client the client with the connection
  * @param game the current game taking place
  */
  public Peer(Client client, NetworkGame game) {
    this.client = client;
    this.game = game;
  }



  /**
  * Runs the thread to recieve network inputs
  */
  @Override
  public void run() {
    String message;
      try {
        if (server != null) {
          message = server.recieve();
        }
        else {
          message = client.recieve();
        }
        game.opponentMove(message);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
  }
}
