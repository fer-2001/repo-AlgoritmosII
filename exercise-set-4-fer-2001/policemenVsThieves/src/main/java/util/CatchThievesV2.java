package util;

/**
 * Greedy Policemen catch thieves.
 *
 * @author sonia
 *
 */

public final class CatchThievesV2{


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
        boolean trap;
        int thiefTrapped=0;
        char police = 'P';
        char thief = 'T';
        char trapped = 'A';
        int aux = 0;
        for(int i = 0; i < seqTP.length; i++){
            trap= false;
            if(seqTP[i] == police){

                if(i != seqTP.length-1){ //Revisa si no estoy en último elemento (No hay más elementos a la der)
                    for(int j = 0; j <= distanceK; j++){
                        if(seqTP[i+j] == thief){ // Busca al ladrón que más lejos esté
                            aux = i+j;
                        }
                    }
                }
                if(aux != 0){
                    trap=true;
                    thiefTrapped++;
                    seqTP[aux] = trapped;
                }


                aux=0;
                if(i!=0){ // Revisa si no estoy en el primer elemento (No hay más elementos a la izq)
                    for(int j = distanceK; j > 0; j--){
                        if(trap) break;
                        if(seqTP[i-j] == thief ){// Busca al ladrón que más lejos esté
                            aux = i-j;
                        }
                    }
                }
                if(aux!=0){
                    thiefTrapped++;
                    seqTP[aux] = trapped;
                }

            }
        }

        return thiefTrapped;
    }

}

