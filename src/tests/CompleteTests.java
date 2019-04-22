package tests;

import hexalpha.engine.Complete;
import hexalpha.controller.game.GameController;
import hexalpha.engine.Game;
import hexalpha.engine.Hex;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
*Used to test public methods in Complete class.
*/
public class CompleteTests {

  /**
  *Ensures that the Complete class compiles.
  */
  @Test
  public void Compiles() {
    Game game = new Game(new GameController());
    Complete complete = new Complete(game.getBoard());
  }

  /**
  *Ensures the the completedGame() method returns the correct winning player.
  */
  @Test
  public void testComplete() {
    Game game = new Game(new GameController());
    Complete complete = new Complete(game.getBoard());

    try {
      for (int i = 0; i < 11; i++) {
        game.move(0, i, Hex.BLUE);
        game.move(1, i, Hex.RED);
      }

      assertEquals("Wrong player won game", Hex.RED, complete.completedGame());
    } catch (NullPointerException e) {
      System.out.println("Couldnt find controller");
      assertEquals("Wrong player won game", Hex.RED, complete.completedGame());
    }
  }
}
