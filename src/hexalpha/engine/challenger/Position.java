package hexalpha.engine.challenger;

import hexalpha.engine.Hex;


/**
* Describes the state of the board after a move has been made
*/
public class Position {
  //the state of the board
  private Hex[][] board;
  //the moved that was made
  private Location location;

  /**
  * Creates a new position state after a move has been made.
  *
  * @param board the state of the board after the move
  * @param location the move that was made.
  */
  public Position(Hex[][] board, Location location) {
    this.board = board;
    this.location = location;
  }

  /**
  * Gets the board state
  *
  * @return the board
  */
  public Hex[][] getBoard() {
    return board.clone();
  }

  /**
  * Gets the move the that was made
  *
  * @return location of move made
  */
  public Location getLocation() {
    return location;
  }
}
