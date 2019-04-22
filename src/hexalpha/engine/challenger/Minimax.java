package hexalpha.engine.challenger;

import java.util.Random;
import hexalpha.engine.Hex;
import hexalpha.engine.Complete;
import java.util.ArrayList;
import java.util.HashSet;

/**
* Implementation of Minimax algorithm with alpha beta pruning as the AI.
*/
public class Minimax extends AI {

  Hex player;
  int maxDepth;
  Position bestMove;
  int bestScore;

  /**
  * Setupts Up the AI for usage
  *
  * @param player which role the AI is playing
  * @param maxDepth the maximum depth of moves to search to.
  */
  public void setup(Hex player, int maxDepth) {
    this.player = player;
    this.maxDepth = maxDepth;
  }

  /**
  * Allows the AI to take a turn
  *
  * @param board the current state of the hex board.
  * @return the position the AI wishes to move to.
  */
  public int[] obtainTurn(Hex[][] board) {
    bestMove = null;
    bestScore = Integer.MAX_VALUE;
    Integer alpha = Integer.MIN_VALUE;
    Integer beta = Integer.MAX_VALUE;
    System.out.println("Score: " + evaluate(new Position(board, null), this.maxDepth, alpha, beta, this.player));
    Location bestLocation = bestMove.getLocation();
    return new int[]{bestLocation.getX(), bestLocation.getY()};
  }

  /**
  * Evaluates the optimal heustric score for board positions
  *
  * @param position the current position on the board
  * @param depth the current depth of tree
  * @param alpha the alpha value for pruning
  * @param beta the beta value for pruning
  * @param player the current player taking a turn
  */
  public int evaluate(Position position, int depth, Integer alpha, Integer beta, Hex player) {
    Complete complete = new Complete(position.getBoard());
    if (depth == 0 || complete.completedGame() != Hex.EMPTY) {
      return heustricScore(position);
    }

    if (player == Hex.BLUE) {
      //lowest possible value
      Integer maxEvaluation = Integer.MIN_VALUE;
      for (Position move: getPossibleMoves(position, Hex.BLUE)) {
        int evaluation = evaluate(move, depth - 1, alpha, beta, Hex.RED);
        maxEvaluation = new Integer(compare(maxEvaluation, evaluation, true));
        alpha = new Integer(compare(alpha, evaluation, true));
        if (depth == this.maxDepth) {
          potentialOptimial(move, maxEvaluation);
        }
        if (beta.intValue() <= alpha.intValue()) {
          break;
        }
      }
      return maxEvaluation;
    }
    else {
      Integer maxEvaluation = Integer.MAX_VALUE;
      for (Position move: getPossibleMoves(position, Hex.RED)) {
        int evaluation = evaluate(move, depth - 1, alpha, beta, Hex.BLUE);
        maxEvaluation = new Integer(compare(maxEvaluation, evaluation, false));
        beta = new Integer(compare(beta, evaluation, false));
        if (depth == this.maxDepth) {
          potentialOptimial(move, maxEvaluation);
        }
        if (beta.intValue() <= alpha.intValue()) {
          break;
        }
      }
      return maxEvaluation;
    }
  }

    /**
    * Compares an integer object to an int primitive.
    *
    * @param numObj the integer object being compared
    * @param num2 the primitive being compared
    * @param maximum whether we are trying to find the maximum of compare
    * @return the largest num between numObj and num2 if maximum is true or smallest if maximum is false
    */
    public int compare(Integer numObj, int num2, boolean maximum){
      int num1 = numObj.intValue();
      if (maximum) {
        if (num1 > num2) {
          return num1;
        }
        return num2;
      }
      else {
        if (num1 < num2) {
          return num1;
        }
        else {
          return num2;
        }
      }
    }

    /**
    * Sets the optimal position to the best for the minimax role.
    *
    * @param location the potential best postition
    * @param score the score of the position
    */
    public void potentialOptimial(Position location, int score) {
      if (bestMove == null) {
        bestMove = location;
        bestScore = score;
      }
      else if (this.player == Hex.RED && score < bestScore) {
        bestMove = location;
        bestScore = score;
      }
      else if (this.player == Hex.BLUE && score > bestScore) {
        bestMove = location;
        bestScore = score;
      }
    }

    /**
    * Finds all the possible moves which can be made by player.
    *
    * @param position the current state of game
    * @param player the player whose turn it is to play.
    */
    public ArrayList<Position> getPossibleMoves(Position position, Hex player) {
      HashSet<Location> validMove = new HashSet<Location>();
      Hex[][] board = position.getBoard();
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
          Hex piece = board[i][j];
          //only runs on non-empty pieces
          if (piece != Hex.EMPTY) {
            //checks if the piece belongs to the player
            if (piece == player) {
              Location location = new Location(i, j);
              for (Location adjacent: location.getAdjacent()) {
                if (board[adjacent.getX()][adjacent.getY()] == Hex.EMPTY) {
                  validMove.add(adjacent);
                }
              }
              for (Location bridge: location.getBridges()) {
                if (board[bridge.getX()][bridge.getY()] == Hex.EMPTY){
                  //checks that there exist pieces to actually connect the bridge
                  if(validBridge(board, bridge, location)) {
                    validMove.add(bridge);
                  }
                }
              }
            }
          }
        }
      }

      //System.out.println("valid moves : " + validMove.size());
      if (validMove.size() == 0) {
        validMove = randomMoves(position);
      }
      return convertToPosition(board, validMove, player);
    }

    /**
    * Determines if a bridge is valid as there are 2 connectors leading to it
    */
    public boolean validBridge(Hex[][] board, Location bridge, Location piece) {
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

    public HashSet<Location> randomMoves(Position position) {
      Random random = new Random();
      Hex[][] board = position.getBoard();
      HashSet<Location> moves = new HashSet<Location>();
      final int boundary = 11;
      int totalMoves = 0;
      while (totalMoves < 10 ) {
        int x = random.nextInt(boundary);
        int y = random.nextInt(boundary);
        if (board[x][y] == Hex.EMPTY) {
          Location move = new Location(x, y);
          moves.add(move);
          totalMoves = moves.size();
        }
      }
      return moves;
    }
    /**
    * Converts a locations to positions.
    *
    * @param board the inital state of the board
    * @param locations where pieces can be put
    * @param player the player puting the pieces
    */
    public ArrayList<Position> convertToPosition(Hex[][] board, HashSet<Location> locations, Hex player) {
      ArrayList<Position> positions = new ArrayList<Position>();
      for (Location location: locations) {
        Hex[][] newBoard = cloneBoard(board);
        newBoard[location.getX()][location.getY()] = player;
        Position newPositon = new Position(newBoard, location);
        positions.add(newPositon);
      }
      return positions;
    }

    public Hex[][] cloneBoard(Hex[][] board) {
      Hex[][] newBoard = new Hex[11][11];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
          newBoard[i][j] = board[i][j];
        }
      }
      return newBoard;
    }

    /**
    * Determines a score for a position on the board
    *
    * @param position the position being evaluated
    */
    public int heustricScore(Position position) {
      Complete complete = new Complete(position.getBoard());
      Hex state = complete.completedGame();
      if (state == Hex.RED) {
        return Integer.MIN_VALUE;
      }
      else if (state == Hex.RED) {
        return Integer.MIN_VALUE;
      }
      else {
        PathFinder path = new PathFinder(position.getBoard());
        int redScore = path.longestPath(Hex.RED);
        int blueScore = path.longestPath(Hex.BLUE);
        return blueScore - redScore;
      }
    }

}
