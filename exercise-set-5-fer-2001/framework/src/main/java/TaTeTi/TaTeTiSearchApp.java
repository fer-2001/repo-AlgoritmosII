package TaTeTi;

import engine.MinMaxAlfaPodaEngine;
import java.util.List;
import java.util.Scanner;

public class TaTeTiSearchApp {


  public static void main(String[] args) {
    TatetiStateAdv aux = new TatetiStateAdv(false,1,1);
    TatetiProblemAd sp1 = new TatetiProblemAd(false,aux);
    MinMaxAlfaPodaEngine<TatetiProblemAd, TatetiStateAdv> engineBfs = new MinMaxAlfaPodaEngine<>(sp1);
    engineBfs.setMaxDepth(3);
    int k = 0;
    aux.setType(true);
    System.out.println(aux);
    while(k<5){
      System.out.println("Ingrese la posicion i del tablero: ");
      Scanner leer=new Scanner(System.in);
      int i = leer.nextInt();
      System.out.println("Ingrese la posicion j del tablero: ");
      leer=new Scanner(System.in);
      int j = leer.nextInt();
      aux.pintar(i,j,true);
      aux.setType(true);
      TatetiStateAdv successBfS = engineBfs.computeSuccessor(aux);
      aux.setType(false);
      aux = successBfS;
      System.out.println(successBfS);
      k++;
    }
  }
}
