package hexalpha.engine;

import hexalpha.engine.network.Peer;
import hexalpha.engine.network.Server;
import hexalpha.engine.network.Client;
import hexalpha.controller.Controller;
import hexalpha.controller.game.GameController;
import hexalpha.engine.Hex;
import java.util.Random;

/**
* Describes networked game of hex.
*/
public class NetworkGame extends Game {

  Hex player;
  Hex opponent;
  Server server;
  Client client;

  /**
  * Creates a new instance of a nework game.
  *
  * @param controller the controller managing rendering of the game
  * @param server the connection to opponent
  */
  public NetworkGame(GameController controller, Server server) {
    super(controller);
    this.server = server;
    this.player = Hex.RED;
    this.opponent = Hex.BLUE;
    requestOpponent();
  }

  /**
  * Creates a new instance of a nework game.
  *
  * @param controller the controller managing rendering of the game
  * @param client the connection to opponent.
  */
  public NetworkGame(GameController controller, Client client) {
    super(controller);
    this.client = client;
    this.player = Hex.BLUE;
    this.opponent = Hex.RED;
    setup();
  }

  /**
  * Allows for client to setup the game of hex
  */
  public void setup() {
    Random rand = new Random();
    int choice = rand.nextInt(1);
    if (choice == 0) {
      send("pass");
      changePlayer();
      requestOpponent();
    }
  }

  /**
  * plays the game of hex
  *
  * @param x the x coordinate of move
  * @param y the y coordinate of move
  */
  public void play(int x, int y) {
      if (isValidMove(x , y)) {
        if (currentPlayer() == player) {
          move(x, y);
          requestOpponent();
        }
      }
  }
  /**
  * Allows the player to make a move.
  *
  * @param x the x coordinate of move
  * @param y the y coordinate of move
  */
  public void move(int x, int y) {
    sendMove(x, y);
    super.move(x, y, player);
  }

  /**
  * Sends the users move to their opponent.
  *
  * @param x the x coordinate of move
  * @param y the y coordinate of move
  */
  public void sendMove(int x, int y) {
    String move = "(" + x + "," + y + ")";
    send(move);
  }

  /**
  * Ends the game by closing the connection
  */
  public void quit() {
    super.quit();
    if (!isComplete()) {
      send("quit");
    }
    try {
      if (server != null) {
        server.close();
      }
      else {
        client.close();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
  * Requests for the move of the opponent.
  */
  public void requestOpponent() {
    Peer peer;
    if (server != null) {
      peer = new Peer(server, this);
    }
    else {
      peer = new Peer(client, this);
    }
    peer.start();
  }

  /**
  * Sends messages to the user.
  *
  * @param message the message being sent
  */
  public void send(String message) {
    if (server != null) {
      server.send(message);
    }
    else {
      client.send(message);
    }
  }

  /**
  * Formats the opponents move to be processed and set to the board.
  *
  * @param move the opponents move.
  */
  public void opponentMove(String move) {
    if (move.equals("quit")) {
      setWinner(player);
      quit();
    }
    else if (move.equals("pass")) {
      changePlayer();
    }
    else {
      String[] coordinates = move.substring(1, move.length() -1 ).split(",");
      int x = Integer.parseInt(coordinates[0]);
      int y = Integer.parseInt(coordinates[1]);
      super.move(x, y, opponent);
    }
  }

  public boolean isConnect() {
    if (server != null) {
      return server.isConnected();
    }
    else {
      return client.isConnected();
    }
  }

  /**
  * Determines if a game of hex has been completed.
  *
  * @return true if the game is complete
  */
  public boolean isComplete() {
    if(super.isComplete()) {
      return true;
    }
    return false;
  }
}
