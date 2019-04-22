package hexalpha.engine.challenger;

import hexalpha.engine.Hex;

abstract public class AI {
  abstract public void setup(Hex player, int maxDepth);
  abstract public String getName();
  abstract public int[] obtainTurn(Hex[][] board);
}
