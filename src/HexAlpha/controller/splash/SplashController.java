package hexalpha.controller.splash;

import hexalpha.controller.Controller;
import hexalpha.engine.Hex;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.sound.sampled.Control;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import hexalpha.engine.Hex;

public class SplashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void playAI(ActionEvent event) {
      Controller.changeToGame(Hex.P_V_AI);
    }

    @FXML
    void playOnline(ActionEvent event) {
      Controller.changeToGame(Hex.ONLINE);
    }

    @FXML
    void playPlayer(ActionEvent event) {
      Controller.changeToGame(Hex.P_V_P);
    }

    @FXML
    void watchAI(ActionEvent event) {
      Controller.changeToGame(Hex.P_V_AI);
    }

    @FXML
    void initialize() {

    }
}
