package hexalpha.engine.challenger;

import java.util.ArrayList;

/**
* Desceibes a location on a board
*/
public class Location {

  private int x;
  private int y;

  /**
  * Creates a location of piece on the board.
  *
  * @param x the x value of the position on the board
  * @param y the y value of the position on the board
  */
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
  * Gets the x coordinate of the piece on the board.
  *
  * @return the x coordinate
  */
  public int getX() {
    return this.x;
  }

  /**
  * Gets the y coordinate of the piece on the board
  *
  * @return the y coordinate
  */
  public int getY() {
    return this.y;
  }

  /**
  * Gets all the surround pieces on the hex board
  *
  * @return the adjacent locations to the current location
  */
  public ArrayList<Location> getAdjacent() {
    int xValues = 0;
    int yValues = 1;
    int[][] locations = {{x + 1, x + 1, x, x, x - 1, x - 1},
                        {y, y + 1, y + 1, y - 1, y, y - 1}};
    ArrayList<Location> adjacent= new ArrayList<Location>();
    for (int i = 0; i < locations[xValues].length; i++) {
      int adjX = locations[xValues][i];
      int adjY = locations[yValues][i];
      if (adjX >= 0 && adjX <= 10 && adjY >= 0 && adjY <= 10) {
        adjacent.add(new Location(adjX , adjY));
      }
    }
    return adjacent;
  }

  /**
  * Gets all the bridges that are possible to make from the current location.
  *
  * @return the location of possible bridges.
  */
  public ArrayList<Location> getBridges() {
    int xValues = 0;
    int yValues = 1;
    //description of adjacent pieces
    int[][] locations = {{x - 1, x + 1, x + 1, x - 1, x + 2, x - 2},
                        {y - 2, y + 2, y - 1, y + 1, y + 1, y - 1}};
    ArrayList<Location> bridges = new ArrayList<Location>();
    for (int i = 0; i < locations[xValues].length; i++) {
      int bridgeX = locations[xValues][i];
      int bridgeY = locations[yValues][i];
      if (bridgeX >= 0 && bridgeX <= 10 && bridgeY >= 0 && bridgeY <= 10) {
        bridges.add(new Location(bridgeX, bridgeY));
      }
    }
    return bridges;
  }

  /**
  * Determines if two locations are equal
  *
  * @param loc the location being compared to the current one.
  * @return true if x and y coordinate are equal
  */
  public boolean equals(Location loc) {
    if (this.x == loc.getX() && this.y == loc.getY()) {
      return true;
    }
    return false;
  }

}
