package hexalpha.engine.challenger;

import hexalpha.engine.Hex;
import hexalpha.engine.Complete;
import java.util.ArrayList;
import java.util.HashSet;

/**
* Implementation of Minimax algorithm with alpha beta pruning as the AI.
*/
public class Minimax {

  Hex player;
  int maxDepth;
  Position bestMove;
  int bestScore;

  /**
  * creates a new Minimax AI along with the max depth to search to.
  *
  * @param player which role the AI is playing
  * @param maxDepth the maximum depth of moves to search to.
  */
  public Minimax(Hex player, int maxDepth) {
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
        alpha = new Integer(compare(alpha, evaluation, false));
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
        for (int j = 0; j < board.length; i++) {
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
                  if (board[bridge.getX()][location.getY()] == Hex.EMPTY && board[location.getY()][bridge.getY()] == Hex.EMPTY) {
                    validMove.add(bridge);
                  }
                }
              }
            }
          }
        }
      }
      return convertToPosition(board, validMove, player);
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
        Hex[][] newBoard = board.clone();
        board[location.getX()][location.getY()] = player;
        Position newPositon = new Position(board, location);
        positions.add(newPositon);
      }
      return positions;
    }

    /**
    * Determines a score for a position on the board
    *
    * @param position the position being evaluated
    */
    public int heustricScore(Position position) {
      return 10;
    }

}
