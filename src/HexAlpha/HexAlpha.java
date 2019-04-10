package hexalpha;

import hexalpha.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class HexAlpha extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Controller.setup(primaryStage);
    Controller.changeToSplash();
  }
}
