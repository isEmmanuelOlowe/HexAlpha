package hexalpha.engine;

/**
* Describes a hex game.
*/
public class Game {

  private Hex winner;
  private Hex turn = Hex.BLUE;
  private Hex[][] gameBoard = new Hex[11][11];

  /**
  * Creates a new blank board.
  */
  public Game() {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        gameBoard[i][j] = Hex.EMPTY;
      }
    }
  }

  /**
  * Returns the current player whose turn it is and inverts the turn to the next player.
  *
  * @return the current player whose turn the game is
  */
  public Hex currentPlayer() {
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
  * adds a hex piece to a location on the board.
  *
  * @param x the x coordinate of the piece being added
  * @param y the y coordinate of the piece being added
  * @param player the player whoses piece is being added
  */
  public void move(int x, int y, Hex player) {
      gameBoard[x][y] = player;
  }

  /**
  * Returns a clone of the game board.
  *
  * @return hex board copy
  */
  public Hex[][] getBoard() {
    return gameBoard.clone();
  }

  /**
  * Determines if a move the player wants to make is valid.
  *
  * @param x the x coordinate of attempted move.
  * @param y the y coordinate of attempted move.
  * @return true if the move is valid
  */
  public boolean isValidMove(int x, int y) {
    if (gameBoard[x][y] == Hex.EMPTY) {
      return true;
    }
    return false;
  }

  /**
  * Determines if a game of hex has been completed.
  *
  * @return true if the game is complete
  */
  public boolean isComplete() {
    Complete complete = new Complete(getBoard());
    winner = complete.completedGame();
    if (winner != Hex.EMPTY) {
      return true;
    }
    return false;
  }

  /**
  * Returns the winner of the game of hex.
  *
  * @return the winner of the game
  */
  public Hex getWinner() {
    return winner;
  }
}
