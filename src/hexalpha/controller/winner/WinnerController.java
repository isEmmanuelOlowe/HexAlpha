package hexalpha.controller.winner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import hexalpha.engine.Hex;

public class WinnerController {

    @FXML
    private Label winner;

    public void initialize(Hex player) {
      winner.setText("The winner is : " + player.toString());
    }
}
