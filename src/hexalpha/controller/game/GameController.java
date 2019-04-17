package hexalpha.controller.game;

import hexalpha.engine.Game;
import hexalpha.controller.Controller;
import hexalpha.engine.Hex;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

/**
* Controller which handles the game stage.
*/
public class GameController {

    private Game game;

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
      if (game.isValidMove(x, y)) {
        Hex player = game.currentPlayer();
        game.move(x, y, player);
        changePiece(player, imageView);
        if (game.isComplete()) {
          Controller.changeToWinner(game.getWinner());
        }
      }
    }

    /**
    * Changes the colour of a piece which has been used.
    *
    * @param player the name of the player which has added a piece to the location.
    * @param imageView where the current image is in the fxml file
    */
    private void changePiece(Hex player, ImageView imageView) {
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
    */
    @FXML
    public void initialize(Hex gameType) {
      //determines the game mode being played
      switch(gameType) {
        case P_V_P:
          game = new Game();
          break;
      }
    }
}
