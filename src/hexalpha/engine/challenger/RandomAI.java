package hexalpha.engine.challenger;

import java.util.Random;
import hexalpha.engine.Hex;

public class RandomAI extends AI{

  private String name = "Random AI";
  private Hex player;
  private int randomMoves;


  public void setup(Hex player, int randomMoves) {
    this.player = player;
    this.randomMoves = randomMoves;
  }

  public String getName() {
    return name;
  }

  public int[] obtainTurn(Hex[][] board) {
    boolean found = false;
    Random random = new Random();
    while (true) {
      int x = random.nextInt(randomMoves);
      int y = random.nextInt(randomMoves);
      if (board[x][y] == Hex.EMPTY) {
        return new int[]{x, y};
      }
    }
  }


}
