package examples.queens;

import conventionalsearch.StateProblem;
import examples.jugs.JugsState;

import java.util.LinkedList;
import java.util.List;

public class QueenStateProblem implements StateProblem<QueenState> {

  int[][] matriz;
  QueenState initial;

  public QueenStateProblem(int i, int j){
    initial = new QueenState(i,j,1);
    initial.pintar(i,j);
  }
  @Override
  public QueenState initialState() {
    return initial;
  }
  // 0 Representa reina, 1 representa que no se puede pintar en ese lugar, 2 representa que ese espacio esta libre
  @Override
  public List<QueenState> getSuccessors(QueenState queenState) {
    int[][] matrizAux = (queenState.getMatriz()).clone();
    List<QueenState> result = new LinkedList<>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (matrizAux[i][j] == 2) {
          QueenState aux = new QueenState();
          aux.setContentOfI(i);
          aux.setContentOfJ(j);
          aux.setMatriz(clonarMatriz(matrizAux));
          aux.pintar(i,j);
          aux.setCantReinas(queenState.getCantReinas() + 1);
          aux.setParent(queenState);
          result.add(aux);
        }
      }
    }
  return result;
  }

  private static int[][] clonarMatriz(int[][] matrix){
    int [][] myInt = new int[matrix.length][];
    for(int i = 0; i < matrix.length; i++)
    {
      int[] aMatrix = matrix[i];
      int   aLength = aMatrix.length;
      myInt[i] = new int[aLength];
      System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
    }
    return myInt;
  }
}
