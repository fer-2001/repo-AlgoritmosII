package TaTeTi;

import adversarysearch.StateAdversary;

import java.util.List;

public class TatetiStateAdv implements StateAdversary {

  private  boolean type;
  private  int[][] matriz;

  TatetiStateAdv parent;
  public TatetiStateAdv(boolean type, int i, int j){
    this.type = type;
    this.matriz = new int[3][3];
    if(type){
      this.matriz[i][j] = 1;
    }
    else{
      this.matriz[i][j] = 2;
    }
  }

  public TatetiStateAdv(){
    this.type = true;
    this.matriz = new int[3][3];
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        matriz[k][l] = 0;
      }
    }

  }
  public TatetiStateAdv(boolean type, int[][] matriz){
    this.type = type;
    this.matriz = clonarMatriz(matriz);
  }
  public void setType(boolean type){
    this.type = type;
  }
  public int[][] getMatriz(){
    return this.matriz;
  }
  @Override
  public boolean isMax() {
    return type; // True is max, False is min
  }

  @Override
  public boolean end() {
    TatetiProblemAd p = new TatetiProblemAd();
    List<TatetiStateAdv> s = p.getSuccessors(this);
    return s.isEmpty() || isSuccess();
  }

  @Override
  public int value() {
    // Checking for Rows for X or O victory.
    for (int row = 0; row < 3; row++)
    {
      if (matriz[row][0] == matriz[row][1] && matriz[row][1] == matriz[row][2])
      {
        if (matriz[row][0] == 1)
          return +10;
        else if (matriz[row][0] == 2)
          return -10;
      }
    }

    // Checking for Columns for X or O victory.
    for (int col = 0; col < 3; col++)
    {
      if (matriz[0][col] == matriz[1][col] && matriz[1][col] == matriz[2][col])
      {
        if (matriz[0][col] == 1)
          return +10;
        else if (matriz[0][col] == 2)
          return -10;
      }
    }

    // Checking for Diagonals for X or O victory.
    if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2])
    {
      if (matriz[0][0] == 1)
        return +10;
      else if (matriz[0][0] == 2)
        return -10;
    }
    if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0])
    {
      if (matriz[0][2] == 1)
        return +10;
      else if (matriz[0][2] == 2)
        return -10;
    }

    // Else if none of them have won then return 0
    return 0;
  }

  @Override
  public Object ruleApplied() {
    return null;
  }

  @Override
  public TatetiStateAdv getParent() {
    return parent;
  }

  public void setParent(TatetiStateAdv s) {
    parent = s;
  }

  @Override
  public boolean isSuccess() {
    if(this.isMax()){
      return value() == 10;
    }
    else {
      return value() == -10;
    }
  }
  public void pintar(int i, int j,boolean type){
    if(matriz[i][j] == 0){
      if(type){
        matriz[i][j] = 1;
      }
      else{
        matriz[i][j] = 2;
      }
    }
  }


  @Override
  public String toString(){
    System.out.println("\n");
    System.out.println( matriz[0][0] + " | " + matriz[0][1] + " | "
            + matriz[0][2]);
    System.out.println("---------");
    System.out.println(matriz[1][0] + " | " + matriz[1][1] + " | "
            + matriz[1][2]);
    System.out.println("---------");
    System.out.println(matriz[2][0] + " | " + matriz[2][1] + " | "
            + matriz[2][2]);
    //System.out.println("\n");
    return "";
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
