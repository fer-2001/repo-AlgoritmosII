import java.util.Arrays;

public class MaxValorVinos {

    private int[][] valores; // Matriz donde se almacenan la ganancia de los vinos segun el año que se venda

    // NON-JAVADOC: Constructor de la clase
    public MaxValorVinos(int[] valorDeLosVinos) {
        int n = valorDeLosVinos.length;
        valores = new int[n+1][n+1]; // Se almacenan valores desde la posicion 1..(n+1)
        for (int[] row : valores)
            Arrays.fill(row, -1);
    }

    // NON-JAVADOC: getter de la matriz perteneciente a la clase MaxValorVinos
    public int[][] getMatriz() {
        return valores;
    }

    /**
     * Funcion recursiva que calcula el mejor costo que puede lograrse vendiendo todos los vinos
     * @param valoresVinos Arreglo que contiene el costo de todos los vinos
     * @param i indice del primer elemento
     * @param j indice del ultimo elemento
     * @param k representa el año en el que se vende el vino
     * @return la mayor ganancia que se puede obtener de vender todos los vinos
     * SOLO ESTÁN TRATADAS LAS EXCEPCIONES SIMPLES.
     */
    public int calculoMaxValor(int[] valoresVinos, int i, int j, int k) {
        int salida;
        
        if(valoresVinos.length == 0){
            throw new UnsupportedOperationException("El arreglo no puede estar vacio");
        }

        // Casos base 1
        if (i > valoresVinos.length-1) return 0;
        if (j < 0) return 0;
        //Caso base de fin de recursion (se recorrieron todos los elementos)
        if (i == j) {
            valores[i+1][k] = valoresVinos[i] * k;
            return valores[i+1][k];
        }

        // Revisa si no se encuentra calculado la ganancia para el año "k", si no es asi, la calcula y almacena en una matriz
        if ((valores[i+1][k]) == (-1)) {
            valores[i+1][k] = valoresVinos[i] * k;
        }
        if ((valores[j+1][k]) == (-1)) {
            valores[j+1][k] = valoresVinos[j] * k;
        }

        salida = Math.max(valores[i+1][k] + calculoMaxValor(valoresVinos, i + 1, j, k + 1),
                valores[j+1][k] + calculoMaxValor(valoresVinos, i, j - 1, k + 1));


        return salida;
    }

    public static void main(String[] args) {
        int[] array = new int[]{200, 340, 150};
        MaxValorVinos aux = new MaxValorVinos(array);
        int[][] matriz = aux.getMatriz();
        int salida = aux.calculoMaxValor(array, 0, array.length - 1, 1);
        // Ciclo para imprimir la matriz por consola
        for (int i = 1; i < matriz.length; i++) {
            for (int j = 1; j < matriz[i].length; j++) {
               System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(salida);
    }
}