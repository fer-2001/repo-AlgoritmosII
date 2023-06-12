package examples.TicTacToe;

import adversarysearch.StateAdversary;
import conventionalsearch.State;

/**
 * TicTacToeStateAdversary
 */
public class TicTacToeStateAdversary implements StateAdversary{
  private TicTacToeStateAdversary parent = null;
  private int[][] table;
  private boolean MAX;

  public TicTacToeStateAdversary(){
    this.table = new int[3][3];
    this.MAX = false;
  }

  public TicTacToeStateAdversary(TicTacToeStateAdversary parent, int[][] table){
    this.parent = parent;
    this.table = table;
  }

  @Override
  public State getParent() {
    return this.parent;
  }

  @Override
  public boolean isSuccess() {
    // Revisamos si hay tres fichas iguales en una fila, columna o diagonal
    for (int i = 0; i < 3; i++) {
      if (table[i][0] != 0 && table[i][0] == table[i][1] && table[i][1] == table[i][2]) {
        return true;
      }
      if (table[0][i] != 0 && table[0][i] == table[1][i] && table[1][i] == table[2][i]) {
        return true;
      }
    }
    if (table[1][1] != 0 && ((table[0][0] == table[1][1] && table[1][1] == table[2][2]) || (table[0][2] == table[1][1] && table[1][1] == table[2][0]))) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isMax() {
    return (MAX == true);
  }

  @Override
  public boolean end() {
    return isSuccess() || isDraw();
  }

  private boolean isDraw(){
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (table[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  public int[][] getTable() {
    return table;
  }

  public void setMAX(boolean MAX) {
    this.MAX = MAX;
  }
  
  public void setParent(TicTacToeStateAdversary parent) {
    this.parent = parent;
  }

  public void setTable(int[][] table) {
    this.table = table;
  }

  @Override
  public int value() {
    for (int i = 0; i < 3; i++) {
      if (table[i][0] != 0 && table[i][0] == table[i][1] && table[i][1] == table[i][2] && table[i][0] == 1) {
        return 1;
      }
      if (table[0][i] != 0 && table[0][i] == table[1][i] && table[1][i] == table[2][i] && table[0][i] == 1) {
        return 1;
      }
      if (table[i][0] != 0 && table[i][0] == table[i][1] && table[i][1] == table[i][2] && table[i][0] == -1) {
        return -1;
      }
      if (table[0][i] != 0 && table[0][i] == table[1][i] && table[1][i] == table[2][i] && table[0][i] == -1) {
        return -1;
      }
    }
    if (table[1][1] != 0 && ((table[0][0] == table[1][1] && table[1][1] == table[2][2]) || (table[0][2] == table[1][1] && table[1][1] == table[2][0])) && table[1][1] == 1) {
      return 1;
    }
    if (table[1][1] != 0 && ((table[0][0] == table[1][1] && table[1][1] == table[2][2]) || (table[0][2] == table[1][1] && table[1][1] == table[2][0])) && table[1][1] == -1) {
      return -1;
    }
    return 0;
  }

  @Override
  public Object ruleApplied() {
    return null;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("  0 1 2\n");
    for (int i = 0; i < table.length; i++) {
      sb.append(i + " ");
      for (int j = 0; j < table[0].length; j++) {
        
        if (table[i][j] == 0) {
          sb.append(" ");
        } else if (table[i][j] == 1) {
          sb.append("0");
        } else{
          sb.append("X");
        }

        if (j != table[0].length - 1) {
          sb.append("|");
        }
      }
      if (i != table.length - 1) {
        sb.append("\n  -+-+-\n");
      }
    }
    sb.append("\n");
    return sb.toString();
  }

  public TicTacToeStateAdversary placeMark(int row, int col, TicTacToeStateAdversary parent) {
    int[][] table = parent.getTable();
    TicTacToeStateAdversary s = new TicTacToeStateAdversary(parent, table);
    table[row][col] = -1;
    s.setTable(table);
    return s;
  }

  public static void main(String[] args) {
    int[][] table = {{-1, 0, 1}
                    ,{0, 0, -1}
                    ,{0, 0, 1}};
    TicTacToeStateAdversary state = new TicTacToeStateAdversary();
    state.setTable(table);
    System.out.println(state);
    System.out.println(state.value());
  }

}