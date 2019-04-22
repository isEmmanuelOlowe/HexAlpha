package hexalpha.engine;

import hexalpha.engine.Hex;
import hexalpha.engine.challenger.AI;
import hexalpha.controller.game.GameController;
/**
* Allows user to play against AI.
*/
public class AIGame extends Game {

  private Hex player;
  private Hex opponent;
  private AI challenger;

  /**
  * Creates a new game against AI.
  *
  * @param controller the game stage that will be printed to.
  * @param challenger the ai the user is playing against.
  */
  public AIGame(GameController controller, AI challenger) {
    super(controller);
    this.player = Hex.BLUE;
    this.opponent = Hex.RED;
    this.challenger = challenger;
    this.challenger.setup(this.opponent, 3);
  }

  /**
  * Plays the game of Hex.
  *
  * @param x the x coordinate of move
  * @param y the y coordinate of move
  */
  public void play(int x, int y) {
    if (isValidMove(x, y)) {
      if (currentPlayer() == player && !isComplete()) {
        super.move(x, y, currentPlayer());
        if (!isComplete()) {
          int[] move = challenger.obtainTurn(getBoard());
          super.move(move[0], move[1], currentPlayer());
        }
      }
    }
  }
}
