package hexalpha.engine.challenger;

import java.util.HashSet;
import java.util.ArrayList;
import hexalpha.engine.Hex;

/**
* Finds the longest path for a piece colour.
*/
public class PathFinder{

  public Hex[][] board;
  public Hex piece;
  HashSet<Location> searched = new HashSet<Location>();

  /**
  * setupts the path finder
  *
  * @param board the current state of the board
  * @param piece the piece whichs longest path is being found.
  */
  public PathFinder(Hex[][] board) {
    this.board = board;
  }

  public int longestPath(Hex piece) {
    int max = 0;
    this.piece = piece;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        Hex content;
        if (piece == Hex.RED) {
          content = board[j][i];
        }
        else {
          content = board[i][j];
        }
        if (content == piece) {
          max = maximum(max, path(i, j));
        }
      }
    }
    return max;
  }

  public boolean searchedBefore(Location location) {
    for (Location old: searched) {
      if (old.equals(location)) {
        return true;
      }
    }
    return false;
  }

  public int maximum(int num1, int num2) {
    if (num1 > num2) {
      return num1;
    }
    return num2;
  }

  public int path(int x, int y) {
    Location location = new Location(x, y);
    int length = 0;
    return path(location, length);
  }

  public int path(Location location, int length) {
    if (searchedBefore(location)) {
      return length;
    }
    else {
      searched.add(location);
    }
    ArrayList<Location> adjs = location.getAdjacent();
    int max = 0;
    for (Location adj: adjs) {
      int newLength = length;
      if (board[adj.getX()][adj.getY()] == piece) {
        if (piece == Hex.RED) {
          if (adj.getX() > location.getX()) {
            newLength++;
          }
        }
        else if (piece == Hex.BLUE){
          if (adj.getY() > location.getY()) {
            newLength++;
          }
        }
        max = maximum(max, path(adj, newLength));
      }
    }

    for (Location brd: location.getBridges()) {
      int newLength = length;
      if (board[brd.getX()][brd.getY()] == piece) {
        if (validBridge(brd, location)) {
          if (piece == Hex.RED){

            if (brd.getX() > location.getX()) {
              newLength += brd.getX() - location.getY();
            }
          }
        else if (piece == Hex.BLUE){
          if (brd.getY() > location.getY()) {
            newLength += brd.getY() - location.getY();
          }
          }
        }
        max = maximum(max, path(brd, newLength));
        }
      }
    return max;
  }

  /**
  * Determines if a bridge is valid as there are 2 connectors leading to it
  */
  public boolean validBridge(Location bridge, Location piece) {
    boolean valid = true;
    for (Location adjB: bridge.getAdjacent()) {
      for (Location adjP: piece.getAdjacent()) {
        if (adjB.equals(adjP)) {
          if (board[adjB.getX()][adjB.getY()] != Hex.EMPTY) {
            valid = false;
          }
        }
      }
    }
    return valid;
  }
}
