import java.util.Arrays;
import java.util.HashMap;

public class MaxValorVinosMemo {
    private Integer[] valoresVinos;
    private static Integer[][] valoresParciales;
    HashMap<Tuple, Integer> cache;

    public MaxValorVinosMemo(Integer[] valores) {
        this.valoresVinos = valores;
        this.cache = new HashMap<>();
        this.valoresParciales = new Integer[valores.length][valores.length];
        for (Integer[] row : valoresParciales)
            Arrays.fill(row, -1);
    }


    public Integer calculoMaxValor(Integer i, Integer j, Integer k) {
        Tuple<Integer, Integer> aux = new Tuple<>(i, j);
        if (valoresVinos.length == 0) {
            throw new UnsupportedOperationException("El arreglo no puede estar vacio");
        }

        // Casos base 1
        if (i >j) return 0;

        // Caso base: contiene el elemento buscado
        if(cache.containsKey(aux)){
            return cache.get(aux);
        }

        Integer maxBeneficioDer = valoresVinos[i]*k + calculoMaxValor(i+1,j,k+1);
        Integer maxBeneficionIzq = valoresVinos[j]*k + calculoMaxValor(i,j-1,k+1);
        Integer max = (Math.max( maxBeneficioDer ,maxBeneficionIzq));
        cache.put(aux,max);

        return max;
    }


    public Integer calculoMaxValorMatriz(Integer i, Integer j, Integer k) {
        if (valoresVinos.length == 0) {
            throw new UnsupportedOperationException("El arreglo no puede estar vacio");
        }

        // Casos base 1
        if (i >j) return 0;

        // Caso base: tengo calculado ya el valor
        if(valoresParciales[i][j] != -1){
            return valoresParciales[i][j];
        }

        Integer maxBeneficioDer = valoresVinos[i]*k + calculoMaxValorMatriz(i+1,j,k+1);
        Integer maxBeneficionIzq = valoresVinos[j]*k + calculoMaxValorMatriz(i,j-1,k+1);
        Integer max = (Math.max( maxBeneficioDer ,maxBeneficionIzq));
        valoresParciales[i][j] = max;

        return max;
    }



    public static void main(String[] args) {
        Integer[] array = new Integer[]{200, 340, 150};
        MaxValorVinosMemo aux = new MaxValorVinosMemo(array);
        MaxValorVinosMemo aux2 = new MaxValorVinosMemo(array);

        Integer salida0 = aux2.calculoMaxValorMatriz(0, array.length -1, 1);
        System.out.println(salida0);
        for (int i = 0; i < valoresParciales.length; i++) {
            for (int j = 0; j < valoresParciales[i].length; j++) {
                System.out.print(valoresParciales[i][j] + " ");
            }
            System.out.println();
        }

        //Integer salida = aux.calculoMaxValor(0, array.length -1, 1);
        //System.out.println(salida);
    }








}
