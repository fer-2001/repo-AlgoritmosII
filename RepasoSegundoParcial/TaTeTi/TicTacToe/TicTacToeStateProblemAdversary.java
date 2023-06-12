package examples.TicTacToe;

import java.util.LinkedList;
import java.util.List;

import adversarysearch.StateProblemAdversary;

public class TicTacToeStateProblemAdversary implements StateProblemAdversary<TicTacToeStateAdversary> {

  private final TicTacToeStateAdversary initial;

  public TicTacToeStateProblemAdversary() {
    this.initial = new TicTacToeStateAdversary();
  }

  public TicTacToeStateProblemAdversary(TicTacToeStateAdversary s) {
    this.initial = s;
  }

  @Override
  public TicTacToeStateAdversary initialState() {
    return initial;
  }

  @Override
  public List<TicTacToeStateAdversary> getSuccessors(TicTacToeStateAdversary s) {
    List<TicTacToeStateAdversary> succs = new LinkedList<TicTacToeStateAdversary>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (s.getTable()[i][j] == 0) {
          TicTacToeStateAdversary succ = new TicTacToeStateAdversary();
          succ.setParent(s);
          succ.setTable(copyTable(s.getTable()));
          succ.setMAX(!s.isMax());

          int player = s.isMax() ? 1 : -1;
          succ.getTable()[i][j] = player;
          succs.add(succ);
        }
      }
    }
    return succs;
  }

  private int[][] copyTable(int[][] table) {
    int[][] newTable = new int[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(table[i], 0, newTable[i], 0, 3);
    }
    return newTable;
  }

  @Override
  public int minValue() {
    return -1;
  }

  @Override
  public int maxValue() {
    return 1;
  }

}
