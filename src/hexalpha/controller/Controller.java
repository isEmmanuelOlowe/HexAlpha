package hexalpha.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.image.Image;
import hexalpha.controller.splash.SplashController;
import hexalpha.controller.game.GameController;
import hexalpha.controller.winner.WinnerController;
import hexalpha.engine.Hex;

/**
* Class used for managing the main stage.
*/
public class Controller {

  private static Stage stage;

  /**
  * Takes the main stage that will be used to diseplay to user.
  *
  * @param primaryStage the main stage that will be manipulated
  */
  public static void setup(Stage primaryStage) {
    stage = primaryStage;
    //The heading of the application
    stage.setTitle("HexAlpha");
    //The program icon
    stage.getIcons().add(new Image("hexalpha/controller/icon.png"));
  }

  /**
  * Changes the stage to the splash screen.
  */
  public static void changeToSplash() {
    try {
      FXMLLoader loader = new FXMLLoader(SplashController.class.getResource("splash.fxml"));
      Parent root = loader.load();
      SplashController splashController = (SplashController) loader.getController();
      //creates a new scene and displays it to the user
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }

  /**
  * Changes the main stage to that of hex game.
  *
  * @param gameType the game mode in which the user is trying to play
  */
  public static void changeToGame(Hex gameType) {
    try {
      FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
      Parent root = loader.load();
      GameController gameController = (GameController) loader.getController();
      gameController.initialize(gameType);
      //creates a new scene and displays it to the user.
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }

  public static void changeToWinner(Hex winner) {
    try {
      FXMLLoader loader = new FXMLLoader(WinnerController.class.getResource("winner.fxml"));
      Parent root = loader.load();
      WinnerController winnerController = (WinnerController) loader.getController();
      winnerController.initialize(winner);
      //creates a new scene and displays it to the user.
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }
}
