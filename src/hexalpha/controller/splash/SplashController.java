package hexalpha.controller.splash;

import hexalpha.controller.Controller;
import hexalpha.engine.Hex;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert.AlertType;
/**
* Controller for splash screen.
*/
public class SplashController {

    /**
    * Enters the game in Player vs AI game mode.
    */
    @FXML
    private void playAI() {
      Controller.changeToGame(Hex.P_V_AI);
    }

    /**
    * Enters the game in Online Game Mode.
    */
    @FXML
    private void playOnline() {
      //asks the user if they wish to host the game to determine if they are the server or client
      ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
      ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
      Alert alert = new Alert(AlertType.CONFIRMATION, "Are you hosting the game?", yes, no);
      alert.setTitle("Game host");
      Optional<ButtonType> result = alert.showAndWait();

      if (result.orElse(yes) == no) {
        Controller.changeToLobby();
      }
      else {
        Controller.changeToWait();
      }
    }

    /**
    * Enters the game in Player vs Player game mode.
    */
    @FXML
    private void playPlayer() {
      Controller.changeToGame(Hex.P_V_P);
    }

    /**
    * Enters the game in AI vs AI game mode.
    */
    @FXML
    private void watchAI() {
      Controller.changeToGame(Hex.AI_V_AI);
    }
}
