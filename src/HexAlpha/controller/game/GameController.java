package hexalpha.controller.game;

import hexalpha.engine.Game;
import hexalpha.engine.Hex;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import javax.swing.text.html.ImageView;
import javafx.scene.image.Image;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import hexalpha.engine.Hex;

public class GameController {

    // @FXML
    // private ResourceBundle resources;
    //
    // @FXML
    // private URL location;
    //
    // @FXML
    // private ImageView hex_0_0;
    //
    // @FXML
    // private ImageView hex_0_1;
    //
    // @FXML
    // private ImageView hex_0_2;
    //
    // @FXML
    // private ImageView hex_0_3;
    //
    // @FXML
    // private ImageView hex_0_4;
    //
    // @FXML
    // private ImageView hex_0_5;
    //
    // @FXML
    // private ImageView hex_0_6;
    //
    // @FXML
    // private ImageView hex_0_7;
    //
    // @FXML
    // private ImageView hex_0_8;
    //
    // @FXML
    // private ImageView hex_0_9;
    //
    // @FXML
    // private ImageView hex_0_10;
    //
    // @FXML
    // private ImageView hex_1_0;
    //
    // @FXML
    // private ImageView hex_1_1;
    //
    // @FXML
    // private ImageView hex_1_2;
    //
    // @FXML
    // private ImageView hex_1_3;
    //
    // @FXML
    // private ImageView hex_1_4;
    //
    // @FXML
    // private ImageView hex_1_5;
    //
    // @FXML
    // private ImageView hex_1_6;
    //
    // @FXML
    // private ImageView hex_1_7;
    //
    // @FXML
    // private ImageView hex_1_8;
    //
    // @FXML
    // private ImageView hex_1_9;
    //
    // @FXML
    // private ImageView hex_1_10;
    //
    // @FXML
    // private ImageView hex_2_0;
    //
    // @FXML
    // private ImageView hex_2_1;
    //
    // @FXML
    // private ImageView hex_2_2;
    //
    // @FXML
    // private ImageView hex_2_3;
    //
    // @FXML
    // private ImageView hex_2_4;
    //
    // @FXML
    // private ImageView hex_2_5;
    //
    // @FXML
    // private ImageView hex_2_6;
    //
    // @FXML
    // private ImageView hex_2_7;
    //
    // @FXML
    // private ImageView hex_2_8;
    //
    // @FXML
    // private ImageView hex_2_9;
    //
    // @FXML
    // private ImageView hex_2_10;
    //
    // @FXML
    // private ImageView hex_3_0;
    //
    // @FXML
    // private ImageView hex_3_1;
    //
    // @FXML
    // private ImageView hex_3_2;
    //
    // @FXML
    // private ImageView hex_3_3;
    //
    // @FXML
    // private ImageView hex_3_4;
    //
    // @FXML
    // private ImageView hex_3_5;
    //
    // @FXML
    // private ImageView hex_3_6;
    //
    // @FXML
    // private ImageView hex_3_7;
    //
    // @FXML
    // private ImageView hex_3_8;
    //
    // @FXML
    // private ImageView hex_3_9;
    //
    // @FXML
    // private ImageView hex_3_10;
    //
    // @FXML
    // private ImageView hex_4_0;
    //
    // @FXML
    // private ImageView hex_4_1;
    //
    // @FXML
    // private ImageView hex_4_2;
    //
    // @FXML
    // private ImageView hex_4_3;
    //
    // @FXML
    // private ImageView hex_4_4;
    //
    // @FXML
    // private ImageView hex_4_5;
    //
    // @FXML
    // private ImageView hex_4_6;
    //
    // @FXML
    // private ImageView hex_4_7;
    //
    // @FXML
    // private ImageView hex_4_8;
    //
    // @FXML
    // private ImageView hex_4_9;
    //
    // @FXML
    // private ImageView hex_4_10;
    //
    // @FXML
    // private ImageView hex_5_0;
    //
    // @FXML
    // private ImageView hex_5_1;
    //
    // @FXML
    // private ImageView hex_5_2;
    //
    // @FXML
    // private ImageView hex_5_3;
    //
    // @FXML
    // private ImageView hex_5_4;
    //
    // @FXML
    // private ImageView hex_5_5;
    //
    // @FXML
    // private ImageView hex_5_6;
    //
    // @FXML
    // private ImageView hex_5_7;
    //
    // @FXML
    // private ImageView hex_5_8;
    //
    // @FXML
    // private ImageView hex_5_9;
    //
    // @FXML
    // private ImageView hex_5_10;
    //
    // @FXML
    // private ImageView hex_6_0;
    //
    // @FXML
    // private ImageView hex_6_1;
    //
    // @FXML
    // private ImageView hex_6_2;
    //
    // @FXML
    // private ImageView hex_6_3;
    //
    // @FXML
    // private ImageView hex_6_4;
    //
    // @FXML
    // private ImageView hex_6_5;
    //
    // @FXML
    // private ImageView hex_6_6;
    //
    // @FXML
    // private ImageView hex_6_7;
    //
    // @FXML
    // private ImageView hex_6_8;
    //
    // @FXML
    // private ImageView hex_6_9;
    //
    // @FXML
    // private ImageView hex_6_10;
    //
    // @FXML
    // private ImageView hex_7_0;
    //
    // @FXML
    // private ImageView hex_7_1;
    //
    // @FXML
    // private ImageView hex_7_2;
    //
    // @FXML
    // private ImageView hex_7_3;
    //
    // @FXML
    // private ImageView hex_7_4;
    //
    // @FXML
    // private ImageView hex_7_5;
    //
    // @FXML
    // private ImageView hex_7_6;
    //
    // @FXML
    // private ImageView hex_7_7;
    //
    // @FXML
    // private ImageView hex_7_8;
    //
    // @FXML
    // private ImageView hex_7_9;
    //
    // @FXML
    // private ImageView hex_7_10;
    //
    // @FXML
    // private ImageView hex_8_0;
    //
    // @FXML
    // private ImageView hex_8_1;
    //
    // @FXML
    // private ImageView hex_8_2;
    //
    // @FXML
    // private ImageView hex_8_3;
    //
    // @FXML
    // private ImageView hex_8_4;
    //
    // @FXML
    // private ImageView hex_8_5;
    //
    // @FXML
    // private ImageView hex_8_6;
    //
    // @FXML
    // private ImageView hex_8_7;
    //
    // @FXML
    // private ImageView hex_8_8;
    //
    // @FXML
    // private ImageView hex_8_9;
    //
    // @FXML
    // private ImageView hex_8_10;
    //
    // @FXML
    // private ImageView hex_9_0;
    //
    // @FXML
    // private ImageView hex_9_1;
    //
    // @FXML
    // private ImageView hex_9_2;
    //
    // @FXML
    // private ImageView hex_9_3;
    //
    // @FXML
    // private ImageView hex_9_4;
    //
    // @FXML
    // private ImageView hex_9_5;
    //
    // @FXML
    // private ImageView hex_9_6;
    //
    // @FXML
    // private ImageView hex_9_7;
    //
    // @FXML
    // private ImageView hex_9_8;
    //
    // @FXML
    // private ImageView hex_9_9;
    //
    // @FXML
    // private ImageView hex_9_10;
    //
    // @FXML
    // private ImageView hex_10_0;
    //
    // @FXML
    // private ImageView hex_10_1;
    //
    // @FXML
    // private ImageView hex_10_2;
    //
    // @FXML
    // private ImageView hex_10_3;
    //
    // @FXML
    // private ImageView hex_10_4;
    //
    // @FXML
    // private ImageView hex_10_5;
    //
    // @FXML
    // private ImageView hex_10_6;
    //
    // @FXML
    // private ImageView hex_10_7;
    //
    // @FXML
    // private ImageView hex_10_8;
    //
    // @FXML
    // private ImageView hex_10_9;
    //
    // @FXML
    // private ImageView hex_10_10;

    private Game game;

    @FXML
    private void move(MouseEvent event) {

      String source = event.getPickResult().getIntersectedNode().getId();
      ImageView imageView = (ImageView) event.getPickResult().getIntersectedNode();
      String[] piece = source.split("_");
      int y = Integer.parseInt(piece[1]);
      int x = Integer.parseInt(piece[2]);
      if (game.isComplete() == false && game.isValidMove(x, y)) {
        Hex player = game.currentPlayer();
        game.move(x, y, player);
        changePiece(player, imageView);
      }
    }

    private void changePiece(Hex player, ImageView imageView) {
      Image image;
      if (Hex.BLUE == player) {
        image = new Image("hexalpha/controller/game/img/blue.png");
      }
      else {
        image = new Image("hexalpha/controller/game/img/red.png");
      }
      imageView.setImage(image);
    }

    @FXML
    public void initialize(Hex gameType) {
      switch(gameType) {
        case P_V_P:
          game = new Game();
          break;
      }
    }
}
