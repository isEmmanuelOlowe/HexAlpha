package hexalpha.engine;

public class Game {

  private Hex winner;
  private Hex turn = Hex.BLUE;
  private Hex[][] gameBoard = new Hex[11][11];
  public Game() {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        gameBoard[i][j] = Hex.EMPTY;
      }
    }
  }

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

  private void initBoard() {

  }

  public void move(int x, int y, Hex player) {
      gameBoard[x][y] = player;
  }

  public Hex[][] getBoard() {
    return gameBoard.clone();
  }

  public boolean isValidMove(int x, int y) {
    if (gameBoard[x][y] == Hex.EMPTY) {
      return true;
    }
    return false;
  }

  public boolean isComplete() {
    Complete complete = new Complete(getBoard());
    winner = complete.completedGame();
    System.out.println(winner);
    if (winner != Hex.EMPTY) {
      return true;
    }
    return false;
  }
}
