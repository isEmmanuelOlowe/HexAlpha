package hexalpha.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import hexalpha.controller.splash.SplashController;
import hexalpha.controller.game.GameController;
import hexalpha.controller.lobby.LobbyController;
import hexalpha.controller.wait.WaitController;
import hexalpha.engine.network.Server;
import hexalpha.engine.network.Client;
import hexalpha.engine.Hex;

/**
* Class used for managing the main stage.
*/
public class Controller {

  private static Stage stage;
  private static Scene gameScene;
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
      gameScene = new Scene(root);
      stage.setScene(gameScene);
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
  * @param server the connection to opponent.
  */
  public static void changeToGame(Hex gameType, Server server) {
    try {
      FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
      Parent root = loader.load();
      GameController gameController = (GameController) loader.getController();
      gameController.initialize(gameType, server);
      //creates a new scene and displays it to the user.
      gameScene = new Scene(root);
      stage.setScene(gameScene);
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }

  /**
  * Changes the main stage to that of hex game
  *
  * @param gameType the game mode in which the user is trying to play
  * @param client the connection to opponent.
  */
  public static void changeToGame(Hex gameType, Client client) {
    try {
      FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
      Parent root = loader.load();
      GameController gameController = (GameController) loader.getController();
      gameController.initialize(gameType, client);
      //creates a new scene and displays it to the user.
      gameScene = new Scene(root);
      stage.setScene(gameScene);
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }
  }
  /**
  * Finds image view of a hex piece
  *
  * @param item the name of the hex piece being looked for.
  * @return image view of the hex piece.
  */
  public static ImageView find(String item) {
    ImageView view = (ImageView) gameScene.lookup(item);
    return view;
  }

  /**
  * Changes to the Lobby stage for connecting to host
  */
  public static void changeToLobby() {
    try {
      FXMLLoader loader = new FXMLLoader(LobbyController.class.getResource("lobby.fxml"));
      Parent root = loader.load();
      LobbyController lobbyController = (LobbyController) loader.getController();
      //creates a new scene and displays it to the user.
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      System.out.println("FXML file is missing");
    }

  }

  /**
  * Changes to the wait stage for waiting for a client opponent.
  */
  public static void changeToWait() {
    try {
      FXMLLoader loader = new FXMLLoader(WaitController.class.getResource("wait.fxml"));
      Parent root = loader.load();
      WaitController lobbyController = (WaitController) loader.getController();
      //creates a new scene and displays it to the user.
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException e) {
      e.printStackTrace();
      System.out.println("FXML file is missing");
    }
  }
}
