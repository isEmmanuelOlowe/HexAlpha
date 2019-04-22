package hexalpha.controller.playground;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import hexalpha.engine.Hex;
import hexalpha.engine.AIPlayground;
import javafx.application.Platform;
import hexalpha.engine.challenger.Minimax;
import hexalpha.engine.challenger.RandomAI;
import hexalpha.controller.Controller;
public class PlaygroundController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text time;

    @FXML
    private Text winrate;

    @FXML
    private Text ai1;

    @FXML
    private Text ai2;
    private boolean active = true;
    @FXML
    private ListView<String> display;
    AIPlayground play;

    @FXML
    public void initialize(Hex type) {
      if (type == Hex.AI_V_AI) {
        Minimax minimax = new Minimax();
        ai1.setText("Blue is " + minimax.getName());
        RandomAI random = new RandomAI();
        ai2.setText("Red is " + random.getName());
        play = new AIPlayground(this, minimax, random);
        play.reset();
      }
    }

    @FXML
    public void back() {
      active = false;
      Controller.changeToSplash();
    }

    public void inactive() {
      active = false;
    }

    @FXML
    void start() {
      play.run();
    }
    public void winrate(String message) {
      active = true;
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
           winrate.setText(message);
         }
       });
    }

    public boolean getActive() {
      return active;
    }
    public void time(String message) {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
           time.setText(message);
         }
       });
    }

    public void add(String message) {
      display.getItems().add(message);
    }
}
