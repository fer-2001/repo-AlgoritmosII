package util;

public final class CatchThievesV1 {


    /**
     * Returns the maximum number of thieves that can be caught.
     * Each policeman can catch only one thief which is at most k away from him.
     * @param seqTP is the sequence of thieves and policemen, t or p.
     * @param distanceK represent the units away from the policemen to catch a thieve.
     * @return the maximum number of thieves that can be caught.
     */
    public final int maxCatch(final char []seqTP, final int distanceK) { // Se queda con el primer ladron que encuentra y no agota la distancia
        //throw new UnsupportedOperationException("method not yet implemented");
        if(distanceK < 0) throw new IllegalArgumentException("Distancia debe ser positiva");
        if(seqTP==null) throw new IllegalArgumentException("Arreglo debe ser distinto de null");
        if(seqTP.length==0) return 0;
        boolean trap = false;
        int thiefTrapped=0;
        char police = 'P';
        char thief = 'T';
        char trapped = 'A';

        for(int i = 0; i < seqTP.length; i++){
            trap= false;
            if(seqTP[i] == police){

                if(i != seqTP.length-1){ //Revisa si no estoy en último elemento (No hay más elementos a la der)
                    for(int j = 0; j <= distanceK; j++){
                        if(seqTP[i+j] == thief){
                            thiefTrapped++;
                            seqTP[i+j] = trapped;
                            trap=true;
                            break;
                        }
                    }
                }


                if(i!=0){ // Revisa si no estoy en el primer elemento (No hay más elementos a la izq)
                    for(int j = distanceK; j > 0; j--){
                        if(trap) break;
                        if(seqTP[i-j] == thief ){
                            thiefTrapped++;
                            seqTP[i-j] = trapped;
                            break;
                        }
                    }
                }

            }
        }

        return thiefTrapped;
    }

}
