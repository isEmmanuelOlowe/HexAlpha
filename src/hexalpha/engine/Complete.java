package hexalpha.engine;

import java.util.ArrayList;

/**
* Determines if a game of hex has been completed and if so returns the winnner.
*/
public class Complete {

  final int min;
  final int max;
  ArrayList<int[]> searchedLocation;
  Hex[][] gameBoard;

  /**
  * Adds the current state of game board.
  *
  * @param gameBoard the current state of game
  */
  public Complete(Hex[][] gameBoard) {
    this.gameBoard = gameBoard;
    //minimum value of index
    this.min = 0;
    //maximum value of index
    this.max = gameBoard.length - 1;
  }

  /**
  * Determines if Red player has won.
  * Finds the path between red sides.
  *
  * @return true if a path exists between both red sides
  */
  private boolean redPath() {
    searchedLocation = new ArrayList<int[]>();
    boolean exists = false;
    for (int i = 0; i < gameBoard.length; i++) {
      if (gameBoard[0][i] == Hex.RED) {
        if (end(new int[]{0, i}, Hex.RED)) {
          exists = true;
        }
      }
    }
    return exists;
  }

  /**
  * Determines if Blue player has won.
  * Finds the path between blue sides.
  *
  * @return true if path exists between both blue sides.
  */
  private boolean bluePath() {
    searchedLocation = new ArrayList<int[]>();
    boolean exists = false;
    for (int i = 0; i < gameBoard.length; i++) {
      if (gameBoard[i][0] == Hex.BLUE) {
        if (end(new int[]{i, 0}, Hex.BLUE)) {
          exists = true;
        }
      }
    }
    return exists;
  }

  /**
  * Determines all the pieces surround the current one which are of the same type.
  *
  * @param x the x coordinate of the current piece.
  * @param y the y coordinate of the current piece.
  * @param type the type of piece of the current piece.
  * @return the list of locations of pieces which are adjacent to the current piece.
  */
  private ArrayList<int[]> getAdjacent(int x, int y, Hex type) {
    //determines which index stores x values
    final int xValues = 0;
    //determines which index stores y values
    final int yValues = 1;
    ArrayList<int[]> adjacent = new ArrayList<int[]>();
    //Desceibes the hexagonal shape of the Hex Board
    int[][] locations = {{x + 1, x + 1, x, x, x - 1, x - 1},
                        {y, y + 1, y + 1, y - 1, y, y - 1}};
    for (int i = 0; i < locations[0].length; i++) {
      Hex piece = get(locations[xValues][i], locations[yValues][i]);
      if (piece != null && piece == type) {
        //adds the piece if it of the same type to the list of adjacent pieces.
        adjacent.add(new int[]{locations[xValues][i], locations[yValues][i]});
      }
    }
    return adjacent;
  }

  /**
  * Determines if there is a path between one side and the other for each player.
  *
  * @param position the current position in which the piece is at
  * @param type the type of the current piece
  * @return true if there is a path
  */
  private boolean end(int[] position, Hex type) {
    //index of the other side for a type
    int index;
    //Which side is the other side for a piece type
    if (type == Hex.RED){
      index = 0;
    }
    else {
      index = 1;
    }
    //Gets all the adjacent pieces to the current one
    ArrayList<int[]> adjacent = getAdjacent(position[0], position[1], type);
    //determines if the piece is at the other side for its type
    if (position[index] == max) {
      return true;
    }
    else {
      //checks for adjacent points if there is path to the otherside for the piece type
      for (int[] adjPosition: adjacent) {
        if (end(adjPosition, type)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
  * Gets a hex piece of valid index.
  *
  * @param x the x coordinate of wanted piece
  * @param y the y coordinate of wanted piece
  * @return the hex piece at that current location if valid
  */
  private Hex get(int x, int y) {
    //deteremines if index is invalid or piece has been serarchedBefore
    if (x < min || x > max || y < min || y > max || searchBefore(x , y)) {
      return null;
    }
    else {
      //adds the piece to the list of searched pieces
      searchedLocation.add(new int[]{x , y});
      return gameBoard[x][y];
    }
  }

  /**
  * Checks if a piece has been searched before.
  *
  * @param x the x coordinate of piece being verified.
  * @param y the y coordinate of piece being verified.
  * @return true if it has been searched before.
  */
  private boolean searchBefore(int x, int y) {
    for (int[] position: searchedLocation) {
      if (position[0] == x && position[1] == y) {
        return true;
      }
    }
    return false;
  }

  /**
  * Checks if the game has been completed.
  *
  * @return the state of completion {EMPTY if no winner}
  */
  public Hex completedGame() {
    if (bluePath()) {
      return Hex.BLUE;
    }
    else if (redPath()) {
      return Hex.RED;
    }
    else {
      return Hex.EMPTY;
    }
  }

}
