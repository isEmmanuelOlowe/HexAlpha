package hexalpha.controller.game;

import hexalpha.engine.Game;
import hexalpha.engine.NetworkGame;
import hexalpha.controller.Controller;
import hexalpha.controller.game.GameController;
import javafx.application.Platform;
import hexalpha.engine.network.Server;
import hexalpha.engine.network.Client;
import hexalpha.engine.Hex;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

/**
* Controller which handles the game stage.
*/
public class GameController {

    private Game game;

    @FXML
    private Label winner;
    /**
    * Makes a valid move when valid hex piece has been clicked.
    *
    * @param event data about call origin
    */
    @FXML
    private void move(MouseEvent event) {

      String source = event.getPickResult().getIntersectedNode().getId();
      ImageView imageView = (ImageView) event.getPickResult().getIntersectedNode();
      String[] piece = source.split("_");
      int y = Integer.parseInt(piece[1]);
      int x = Integer.parseInt(piece[2]);
      game.play(x , y);
    }

    /**
    * Changes the colour of a piece which has been used.
    *
    * @param player the name of the player which has added a piece to the location.
    * @param imageView where the current image is in the fxml file
    */
    public void changePiece(Hex player, ImageView imageView) {
      Image image;
      if (Hex.BLUE == player) {
        image = new Image("hexalpha/controller/game/img/blue.png");
      }
      else {
        image = new Image("hexalpha/controller/game/img/red.png");
      }
      //replaces empty with the new image
      imageView.setImage(image);
    }

    /**
    * Initilises the game controller.
    *
    * @param gameType the type of game being played.
    */
    @FXML
    public void initialize(Hex gameType) {
      //determines the game mode being played
      switch(gameType) {
        case P_V_P:
          game = new Game(this);
          break;
      }
    }

    /**
    * Initializes the game controller for a Network Game.
    *
    * @param gameType the type of game being played
    * @param server the connection to client
    */
    @FXML
    public void initialize(Hex gameType, Server server) {
      if (gameType == Hex.ONLINE) {
        game = new NetworkGame(this, server);
      }
    }

    /**
    * Initalize the game controller for a networked Game.
    *
    * @param gameType the type of game being played
    * @param client the connection to server
    */
    @FXML
    public void initialize(Hex gameType, Client client) {
      if (gameType == Hex.ONLINE) {
        game = new NetworkGame(this, client);
      }
    }

    /**
    * Displays the winner of the game
    *
    * @param player the player who won.
    */
    public void displayWinner(Hex player) {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
           winner.setText("The Winner is: " + player);
           winner.setVisible(true);
         }
       });
    }

    /**
    * ends the game.
    */
    @FXML
    private void quit() {
      game.quit();
      Controller.changeToSplash();
    }
}
