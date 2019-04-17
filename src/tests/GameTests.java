package tests;

import hexalpha.engine.Game;
import hexalpha.engine.Hex;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;



public class GameTests {

  @Test
  public void compiles() {
    Game game = new Game();
  }

  @Test
  public void testValidMove() {
    Game game = new Game();
    game.move(0 ,0, Hex.BLUE);
    assertFalse("This is not a valid move",game.isValidMove(0, 0));
  }

  @Test
  public void testWinner() {
    Game game = new Game();

    for (int i = 0; i < 11; i++) {
      game.move(0, i, Hex.BLUE);
      game.move(1, i, Hex.RED);
    }

    game.isComplete();

    assertEquals("Expected winner BLUE  did not win. Winner: " + game.getWinner(), Hex.BLUE ,game.getWinner());

  }

  @Test
  public void testMove() {
    Game game = new Game();
    game.move(5, 6, Hex.BLUE);
    Hex piece = game.getBoard()[5][6];
    assertEquals("Expected piece to be at position", Hex.BLUE, piece);
  }

}
