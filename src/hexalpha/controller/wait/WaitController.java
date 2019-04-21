package hexalpha.controller.wait;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import hexalpha.engine.Hex;
import hexalpha.engine.network.Server;
import hexalpha.engine.network.WaitForClient;
import hexalpha.controller.Controller;

/**
* The view where the host of the game waits.
*/
public class WaitController {


    @FXML
    private Text ip;

    @FXML
    private Text opponent;

    @FXML
    private Label error;

    @FXML
    private Button enter;

    private WaitForClient wait;

    private Server server;

    /**
    * Goes back to the splash page
    */
    @FXML
    private void back() {
      if (server != null){
        if (server.isConnected()){
          //rejects game if connected to a client
          server.send("reject");
        }
        server.close();
      }
      Controller.changeToSplash();
    }

    /**
    * Setups up the controller
    */
    @FXML
    private void initialize() {
      try {
        //creates a new server to connect to client
        server = new Server();
        ip.setText("Your IP: " + server.getIP());
        wait = new WaitForClient(server, this);
        wait.start();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    /**
    * informs the user other player has connected.
    */
    public void game() {
      server.beginGame();
      opponent.setText("Found Opponent");
      enter.setDisable(false);
    }

    /**
    * Enters the game of hex.
    */
    @FXML
    public void enterGame() {
      server.send("ready");
      Controller.changeToGame(Hex.ONLINE, server);
    }
}
