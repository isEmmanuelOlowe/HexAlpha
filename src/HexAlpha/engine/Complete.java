package hexalpha.engine;

import java.util.ArrayList;

public class Complete {

  final int min;
  final int max;
  ArrayList<int[]> searchedLocation;
  Hex[][] gameBoard;

  public Complete(Hex[][] gameBoard) {
    this.gameBoard = gameBoard;
    this.min = 0;
    this.max = gameBoard.length - 1;
  }

  public boolean redPath() {
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

  public boolean bluePath() {
    searchedLocation = new ArrayList<int[]>();
    boolean exists = false;
    for (int i = 0; i < gameBoard[0].length; i++) {
      if (gameBoard[i][0] == Hex.BLUE) {
        if (end(new int[]{i, 0}, Hex.BLUE)) {
          exists = true;
        }
      }
    }
    return exists;
  }

  public ArrayList<int[]> getAdjacent(int x, int y, Hex type) {
    final int xValues = 0;
    final int yValues = 1;
    ArrayList<int[]> adjacent = new ArrayList<int[]>();
    int[][] locations = {{x + 1, x + 1, x, x, x - 1, x - 1},
                        {y, y + 1, y + 1, y - 1, y, y - 1}};
    for (int i = 0; i < locations[0].length; i++) {
      Hex piece = get(locations[xValues][i], locations[yValues][i]);
      if (piece != null && piece == type) {
        adjacent.add(new int[]{locations[xValues][i], locations[yValues][i]});
      }
    }
    return adjacent;
  }

  public boolean end(int[] position, Hex type) {
    int index;
    if (type == Hex.RED){
      index = 0;
    }
    else {
      index = 1;
    }

    ArrayList<int[]> adjacent = getAdjacent(position[0], position[1], type);
    if (position[index] == max) {
      return true;
    }
    else {
      //checks for adjacent points if there are any checks that they are connected to something else
      for (int[] adjPosition: adjacent) {
        if (end(adjPosition, type)) {
          return true;
        }
      }
    }
    return false;
  }


  public Hex get(int x, int y) {
    if (x < min || x > max || y < min || y > max || searchBefore(x , y)) {
      return null;
    }
    else {
      searchedLocation.add(new int[]{x , y});
      return gameBoard[x][y];
    }
  }

  public boolean searchBefore(int x, int y) {
    for (int[] position: searchedLocation) {
      if (position[0] == x && position[1] == y) {
        return true;
      }
    }
    return false;
  }

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
