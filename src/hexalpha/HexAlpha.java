package hexalpha;

import hexalpha.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
* initalisation of JavaFX project.
*/
public class HexAlpha extends Application {

  /**
  * Launches application.
  *
  * @param args the command line arguments passed to program
  */
  public static void main(String[] args) {
    launch(args);
  }

  /**
  * Initial setup of stage.
  *
  * @param primaryStage the main stage that program will use.
  */
  @Override
  public void start(Stage primaryStage) {
    Controller.setup(primaryStage);
    Controller.changeToSplash();
  }
}
