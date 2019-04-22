package hexalpha.engine;

import hexalpha.engine.challenger.AI;
import hexalpha.controller.playground.PlaygroundController;

public class AIPlayground extends Thread {

  private AI competitor1;
  private AI competitor2;
  private int moves = 0;
  private Double comp1Wins = 0.0;
  private Double comp2Wins = 0.0;
  private Hex[][] gameBoard = new Hex[11][11];
  private Hex winner;
  private Hex turn = Hex.BLUE;
  private PlaygroundController controller;
  // private final Date createdDate = new java.util.Date();

  public AIPlayground(PlaygroundController controller, AI competitor1, AI competitor2){
    this.controller = controller;
    this.competitor1 = competitor1;
    this.competitor2 = competitor2;
    this.competitor1.setup(Hex.BLUE, 2);
    this.competitor2.setup(Hex.RED, 1);
  }

  public void reset() {
    moves = 0;
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        gameBoard[i][j] = Hex.EMPTY;
      }
    }
  }
  public Hex changePlayer() {
    Hex current;
    current = turn;
    if (turn == Hex.BLUE){
      turn = Hex.RED;
    }
    else {
      turn = Hex.BLUE;
    }
    return current;
  }

  /**
  * Runs the AIs against each other
  */
  @Override
  public void run() {
    while(controller.getActive()) {
      isComplete();
      if (turn == Hex.BLUE) {
        int[] move = competitor1.obtainTurn(gameBoard);
        move(move[0], move[1], turn);
      }
      else {
        int[] move = competitor2.obtainTurn(gameBoard);
        move(move[0], move[1], turn);
      }
      moves++;
      controller.time("Total moves: " + moves);
      changePlayer();
    }
  }

  public void move(int x, int y, Hex player) {
    gameBoard[x][y] = player;
  }

  /**
  * Determines if a game of hex has been completed.
  *
  * @return true if the game is complete
  */
  public boolean isComplete() {
    Complete complete = new Complete(gameBoard);
    winner = complete.completedGame();
    if (winner != Hex.EMPTY) {
      controller.add("The winner is: " + winner);
      reset();
      if (winner == Hex.BLUE) {
        comp1Wins += 1;
      }
      else {
        comp2Wins += 1;
      }
      if (comp2Wins == 0) {
        controller.winrate("100%");
      }
      else {
        controller.winrate(comp1Wins/comp2Wins + "%");
      }
      return true;
    }
    return false;
  }
}
