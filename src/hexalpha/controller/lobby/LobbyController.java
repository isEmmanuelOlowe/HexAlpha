package hexalpha.controller.lobby;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import hexalpha.controller.Controller;
import java.lang.NumberFormatException;
import hexalpha.engine.Hex;
import hexalpha.engine.network.Client;

/**
* The Lobby where client connects to server
*/
public class LobbyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField opponent;

    @FXML
    private Label error;

    /**
    * goes back to splash page.
    */
    @FXML
    void back() {
      Controller.changeToSplash();
    }

    /**
    * Attempts to connect to server.
    */
    @FXML
    public void connect() {
      Client client;
      String IP = opponent.getText();
      if (validIP(IP)){
        try {
          error.setVisible(true);
          error.setText("Waiting for Server");
          client = new Client(IP);
          //waits for server
          client.beginGame();
          String state = client.recieve();
          if (state.equals("ready")) {
            //the server has acceptedthe game.
            Controller.changeToGame(Hex.ONLINE, client);
          }
          else if (state.equals("reject")) {
            //informs user the server has rejected the game
            error.setText("Server Rejected Request");
          }
        }
        catch(Exception e) {
          error.setText("invalid IP address");
          error.setVisible(true);
        }
      }
      else {
        //in event the ip address of server is invalid
        error.setText("invalid IP address");
        error.setVisible(true);
      }
    }

    /**
    * Determines if an ip address is valid
    */
    private boolean validIP(String IP) {
      String ipAddress = IP;
      String[] splitIP = ipAddress.split("\\.");
      boolean pass = true;
      //Makes sure its in the correct format
      if(splitIP.length == 4){
        for(int i = 0; i < 4; i++){
          //Makes sure that they are all integers
          try {
            Integer.parseInt(splitIP[i]);
          }
          catch (NumberFormatException e) {
            pass = false;
          }
        }
      }
      else {
       pass = false;
      }
      return pass;
    }

}
