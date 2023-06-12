package TaTeTi;

import adversarysearch.StateProblemAdversary;

import java.util.LinkedList;
import java.util.List;

public class TatetiProblemAd implements StateProblemAdversary<TatetiStateAdv> {
  private TatetiStateAdv  initial;

  public TatetiProblemAd(boolean type, TatetiStateAdv state){
    initial = state;
    initial.setType(type);
  }

  public TatetiProblemAd(){
    initial = new TatetiStateAdv();
  }

  @Override
  public TatetiStateAdv initialState() {
    return initial;
  }

  @Override
  public List<TatetiStateAdv> getSuccessors(TatetiStateAdv tatetiStateAdv) {
    int[][] aux = tatetiStateAdv.getMatriz();

    List<TatetiStateAdv> list = new LinkedList<>();
    for(int i = 0; i < 3; i++){
      for(int j=0; j < 3; j++){
        if((aux[i][j] == 1) || (aux[i][j] == 2)){
          continue;
        }
        else{
          if(tatetiStateAdv.isMax()){ // Max = 1, min = 2
            int [][] clon = clonarMatriz(aux);
            clon[i][j] = 2; // El signo opuesto, ya que es turno del oponente
            TatetiStateAdv succ = new TatetiStateAdv(false, clon);
            succ.setParent(tatetiStateAdv);
            list.add(succ);
          }
          else{
            int [][] clon = clonarMatriz(aux);
            clon[i][j] = 1; // El signo opuesto, ya que es turno del oponente
            TatetiStateAdv succ = new TatetiStateAdv(true, clon);
            succ.setParent(tatetiStateAdv);
            list.add(succ);
          }
        }
      }
    }
    return list;
  }

  @Override
  public int minValue() {
    return -10;
  }

  @Override
  public int maxValue() {
    return 10;
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

