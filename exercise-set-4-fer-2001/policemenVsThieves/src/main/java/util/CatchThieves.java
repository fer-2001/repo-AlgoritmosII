package util;

/**
 * Greedy Policemen catch thieves.
 *
 * @author sonia
 *
 */

public final class CatchThieves {
   
  
  /**
   * Returns the maximum number of thieves that can be caught.
   * Each policeman can catch only one thief which is at most k away from him.
   * @param seqTP is the sequence of thieves and policemen, t or p.
   * @param distanceK represent the units away from the policemen to catch a thieve.
   * @return the maximum number of thieves that can be caught.
   */
  public int maxCatch(final char []seqTP, final int distanceK) { // Busca el ladron hasta que no tiene más distancia
    if(distanceK < 0) throw new IllegalArgumentException("Distancia debe ser positiva");
    if(seqTP==null) throw new IllegalArgumentException("Arreglo debe ser distinto de null");
    if(seqTP.length==0) return 0;

    int k=0;
    int z=0;
    int n = seqTP.length;
    int thiefTrapped=0;
    char police = 'P';
    int[] indexOfPolice = new int[n];
    int[] indexOfThiefs= new int[n];

    // Carga de las posiciones donde está cada policia y cada ladron
    for(int i=0; i<n; i++){
      if(seqTP[i] == police){
        indexOfPolice[k]=i;
        k++;
      }
      else{
        indexOfThiefs[z] = i;
        z++;
      }
    }

    int aux = z;
    int aux2= k;
    k =0;
    z=0;
    if(aux2==0 || aux == 0){ // Caso todos policias o todos ladrones
      return thiefTrapped;
    }

    // Caso general
    while(k<aux2){
      if( Math.abs((indexOfPolice[k] - indexOfThiefs[z])) <= distanceK){ // Si está dentro del rango del policia, lo atrapa
        k++;
        z++;
        thiefTrapped++;
      }
      else{
        if(indexOfPolice[k] < indexOfThiefs[z]){ // Si el policia está atras del ladron, se pasa al sig policia
          k++;
        }
        else if (indexOfPolice[z] < indexOfThiefs[k]){ // Si el ladron está atras del policia, se busca al sig ladron
          z++;
        }
      }
    }

    return thiefTrapped;
  }
}

