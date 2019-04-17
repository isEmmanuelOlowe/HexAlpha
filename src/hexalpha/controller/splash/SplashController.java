package hexalpha.controller.splash;

import hexalpha.controller.Controller;
import hexalpha.engine.Hex;
import java.net.URL;
import javafx.fxml.FXML;

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
      Controller.changeToGame(Hex.ONLINE);
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
      Controller.changeToGame(Hex.P_V_AI);
    }
}
