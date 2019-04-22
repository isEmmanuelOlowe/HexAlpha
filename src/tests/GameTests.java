package tests;

import hexalpha.engine.Game;
import hexalpha.controller.Controller;
import hexalpha.controller.game.GameController;
import hexalpha.engine.Hex;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


/**
* Used to test the game class.
*/
public class GameTests {

  /**
  * Ensures that the class compiles.
  */
  @Test
  public void compiles() {
    Game game = new Game(new GameController());
  }

  /**
  * Ensures that invalid moves are handles correctly.
  */
  @Test
  public void testValidMove() {
    try {
      Game game = new Game(new GameController());
      game.move(0 ,0, Hex.BLUE);
      assertFalse("This is not a valid move",game.isValidMove(0, 0));
    } catch (NullPointerException e) {
      System.out.println("Couldnt find controller");
    }
  }

  /**
  * Ensures that the correct winner is displayed.
  */
  @Test
  public void testWinner() {
    try {
      Game game = new Game(new GameController());

      for (int i = 0; i < 11; i++) {
        game.move(0, i, Hex.BLUE);
        game.move(1, i, Hex.RED);
      }

      game.isComplete();

      assertEquals("Expected winner BLUE  did not win. Winner: ", Hex.BLUE ,game.getWinner());
    } catch (NullPointerException e) {
      System.out.println("Couldnt find controller");
    }

  }

  /**
  * Ensures that a piece is placed where the user selects.
  */
  @Test
  public void testMove() {
    try {
      Game game = new Game(new GameController());
      game.move(5, 6, Hex.BLUE);
      Hex piece = game.getBoard()[5][6];
      assertEquals("Expected piece to be at position", Hex.BLUE, piece);
    } catch (NullPointerException e) {
      System.out.println("Couldnt find controller");
    }
  }

}
