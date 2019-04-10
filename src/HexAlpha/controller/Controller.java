package hexalpha.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import hexalpha.controller.splash.SplashController;
import java.io.IOException;
import javafx.scene.image.Image;

public class Controller {

  private static Stage stage;

  public static void setup(Stage primaryStage) {
    stage = primaryStage;
    stage.setTitle("HexAlpha");
    stage.getIcons().add(new Image("hexalpha/controller/icon.png"));
  }

  public static void changeToSplash() {
    try {
      FXMLLoader loader = new FXMLLoader(SplashController.class.getResource("splash.fxml"));
      Parent root = loader.load();
      SplashController splashController = (SplashController) loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }
}
