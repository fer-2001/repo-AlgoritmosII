import java.util.HashMap;

public class Monedas {

  HashMap<Integer,Integer> cache;
  Integer vuelto;
  Integer[] denominacionMonedas;

  public Monedas(Integer vuelto, Integer[] denominacionMonedas){
    this.vuelto = vuelto;
    this.denominacionMonedas = denominacionMonedas;
    this.cache = new HashMap<>();
  }


  public  int cantmonedas( int i, int j){ // indice donde comienza = i; indice donde termina= j (las denominaciones)
    int salida = 0;
    int vueltoAux = vuelto;
    if(vuelto==0){
      return 0;
    }
    while(i<j && vueltoAux >= 0){
      if(denominacionMonedas[i] <= vueltoAux){
        vueltoAux = vueltoAux - denominacionMonedas[i];
        salida++;
      }
      else{
        i++;
      }
    }
  return  salida;
  }


  public static void main(String[] args){
    Integer vuelto = 25;
    Integer[] monedas = {1111,11111,11111,11111,1111111,11111,15555,50000,5500,5000,2000,1000,900,700,600,500,400,300,200,100,50,7,5,1};
    Monedas aux = new Monedas(vuelto,monedas);
    //System.out.println(aux.cantmonedas(0,monedas.length));
    int min = 9999;
    int minActual=9999;
    for(int i = 0; i <= monedas.length; i++){
      minActual = Math.min(aux.cantmonedas(i,monedas.length), aux.cantmonedas(i+1,monedas.length));
      if (minActual != 0) min = Math.min(minActual,min);
    }
    System.out.println(min);
  }


}
