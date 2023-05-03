import java.util.Arrays;

public class ShortesPair {

    Pair punto;


    private static void mergeSort(Pair[] inputArray,int caso) {
        int inputLength = inputArray.length;
        // Caso base para cortar la recursion (tenemos un unico nodo en este punto)
        if (inputLength < 2) {
            return;
        }

        int midIndex = inputLength / 2;
        Pair[] leftHalf = new Pair[midIndex];
        Pair[] rightHalf = new Pair[inputLength - midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = inputArray[i];
        }

        mergeSort(leftHalf,caso);
        mergeSort(rightHalf,caso);

        merge(inputArray, leftHalf, rightHalf,caso);
    }

/*
    Metodo privado para realizar el ordenamiento por el metodo merge.
    Toma el arreglo de entrada, y dos arreglos "mitades" para realizar la recursividad.
    En el metodo publico solo se provee el arreglo de entrada.

*/

    private static void merge (Pair[] inputArray, Pair[] leftHalf, Pair[] rightHalf, int caso) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        int i = 0, j = 0, k = 0;

        switch (caso){
            case 1:
                while (i < leftSize && j < rightSize) {
                    if (Pair.compare(leftHalf[i],rightHalf[j])==-1) {
                        inputArray[k] = leftHalf[i];
                        i++;
                    }
                    else {
                        inputArray[k] = rightHalf[j];
                        j++;
                    }
                    k++;
                }

                while (i < leftSize) {
                    inputArray[k] = leftHalf[i];
                    i++;
                    k++;
                }

                while (j < rightSize) {
                    inputArray[k] = rightHalf[j];
                    j++;
                    k++;
                }
            case 2:
                while (i < leftSize && j < rightSize) {
                    if (Pair.compare2(leftHalf[i],rightHalf[j])==-1) {
                        inputArray[k] = leftHalf[i];
                        i++;
                    }
                    else {
                        inputArray[k] = rightHalf[j];
                        j++;
                    }
                    k++;
                }

                while (i < leftSize) {
                    inputArray[k] = leftHalf[i];
                    i++;
                    k++;
                }

                while (j < rightSize) {
                    inputArray[k] = rightHalf[j];
                    j++;
                    k++;
                }

        }



    }

    public static Pair[] shortesPair(Pair[] puntos){
        if(puntos.length == 2){
            return puntos;
        }
        if(puntos.length == 3){
            return menorDist3Puntos(puntos[0],puntos[1],puntos[2]);
        }
        int inputLength = puntos.length;
        int midIndex = inputLength/2;

        Pair[] mitadIzq = new Pair[midIndex];
        Pair[] mitadDer = new Pair[inputLength-midIndex];

        for (int i = 0; i < midIndex; i++) {
            mitadIzq[i] = puntos[i];
        }
        for (int i = midIndex; i < inputLength; i++) {
            mitadDer[i - midIndex] = puntos[i];
        }
        Pair[] menorIzq = shortesPair(mitadIzq);
        Pair[] menorDer = shortesPair(mitadDer);
        Double distIzq =  distPuntos(menorIzq[0],menorIzq[1]);
        Double distDer = distPuntos(menorDer[0],menorDer[1]);
        Double d = Math.min(distIzq,distDer);
        Pair[] secMid = midSecuence(mitadIzq,mitadDer,puntos,d);
        Double distMid = distPuntos(secMid[0],secMid[1]);
        if(distMid<d){
            return secMid;
        }
        else{
            if(distIzq<distDer){
                return menorIzq;
            }
            else{
                return menorDer;
            }
        }

    }

    private static Pair[] midSecuence(Pair[] mitadIzq, Pair[] mitadDer, Pair[] puntos, Double d){
        int k=0;
        int inputLength = puntos.length;
        int midIndex = inputLength/2;
        int j=midIndex-1;
        Pair[] midSecuence = new Pair[mitadIzq.length+mitadDer.length];
        for(int i=0; i<mitadDer.length; i++){
            if(mitadDer[i].getFirst() <= puntos[j].getFirst()+d){
                midSecuence[i] = mitadDer[i];
                k=i;
            }
        }
        k++;
        if (k==1){
            return midSecuence;
        }

        for(int i=0; i<mitadIzq.length; i++){
            if(mitadIzq[i].getFirst() >= puntos[j].getFirst()-d){
                midSecuence[k] = mitadIzq[i];
                k++;
            }
        }

        mergeSort(midSecuence,2);
        Pair[] minMid = shortesPair(midSecuence);
        return minMid;
    }

    private static Pair[] menorDist3Puntos(Pair p1, Pair p2, Pair p3){
        Pair[] salida = new Pair[2];
        Double menor1 = distPuntos(p1,p2);
        Double menor2 = distPuntos(p2,p3);
        Double menor3 = distPuntos(p1,p3);
        Double menor = Math.min(menor3, (Math.min(menor1,menor2))) ;
        if (menor.equals(menor1)){
            salida[0]=p1;
            salida[1] = p2;
            return salida;
        }
        else if (menor.equals(menor2)){
            salida[0]=p2;
            salida[1] = p3;
            return salida;
        }
        else{
            salida[0]=p1;
            salida[1] = p3;
            return salida;
        }
    }

    private static Double distPuntos(Pair p1, Pair p2){
        if(p1 == null || p2 == null){
            return (double) Integer.MAX_VALUE;
        }
        Integer aux1 = p2.getFirst()-p1.getFirst() ;
        Integer aux2 = p2.getSecond() - p1.getSecond() ;
        double aux =  (aux1*aux1) + (aux2*aux2);
        return Math.sqrt( aux );
    }

    public static void main(String[] args){
        Pair p1 = new Pair(2,2);
        Pair p2 = new Pair(8,0);
        //Pair p3 = new Pair(1,3);
        Pair p4 = new Pair(0,3);
        Pair p5 = new Pair(5,1);
        Pair p6 = new Pair(7,5);
        //Pair p9 = new Pair(2,4);
        //Pair p10 = new Pair(3,4);
        Pair p11 = new Pair(1,0);
        //Pair p12 = new Pair(1,2);
        Pair p13 = new Pair(5,3); // Caso de fallo
        Pair p14 = new Pair(6,3); // Caso de fallo
        Pair p15 = new Pair(5,6);
        //Pair p16 = new Pair(8,3);
        //Pair p17 = new Pair(8,2);
        Pair p18 = new Pair(10,1);
        Pair p19 = new Pair(10,5);
        Pair p20 = new Pair(10,3);
        Pair p21 = new Pair(6,0);


        //System.out.println("P1: " + p1 + " P2: " + p2);
        //System.out.println("Distancia entre p1 y p2 es: " + distPuntos(p2,p1));
        //System.out.println("Distancia entre p1 y p3 es: " + distPuntos(p3,p1));
        //System.out.println("Distancia entre p3 y p2 es: " + distPuntos(p3,p2));
        //System.out.println("Menor dist entre p1,p2,p3: " + Arrays.toString(menorDist3Puntos(p1,p2,p3)));
        Pair[] array = new Pair[]{p1,p2,p4,p5,p6,p11,p13,p14,p15,p18,p19,p20};
        mergeSort(array,1);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(shortesPair(array)));
        System.out.println("Menor dist entre p1,p2,p3: " + Arrays.toString(menorDist3Puntos(p13,p15,p5)));

    }

}
