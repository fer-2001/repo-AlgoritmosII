public class Coins {
  

  /* precondition: coins[] debe contener la moneda de valor 1 y debe estar ordenado */
  public static int changeMakingDyn(int[] coins, int change){
    
    int[][] matriz = new int[coins.length][change+1];

    if (coins.length==0 || change==0) throw new IllegalArgumentException("error");

    for (int i = 0; i < coins.length; i++){
      for (int j = 0; j <= change; j++){

        if (coins[i] == 1)  // moneda valor 1
          matriz[i][j] = j;
                  
        else if (j == 0) // cambio nulo, toda la columna es 0
          matriz[i][j] = 0;
        

        else if ( j >= coins[i] ) // el cambio (actual) es mayor o igual a la moneda (actual)
          matriz[i][j] = Math.min( matriz[i-1][j], 1 + matriz[i][j - coins[i]]);
        else
          matriz[i][j] = matriz[i-1][j];
      }
    }

    return matriz[coins.length-1][change];
  }

  public static void main(String[] args){

    int[] c = {1,5,7,20};
    System.out.println(changeMakingDyn(c,12));

    int[] c1 = {1,3,4};
    System.out.println(changeMakingDyn(c1,6));
  }

}