
public class Vinoteca {

    private int[] valoresVinos; // arreglo que contiene el valor de los vinos

    /**
     * Constructor de la clase Vinoteca
     * @param valoresVinos lista de valores de los vinos
     */
    public Vinoteca(int[] valoresVinos){
        this.valoresVinos = valoresVinos;
    }

    /**
     * Metodo que calcula el maximo benificio de vender una lista de vinos
     * con la condicion de solamente vender el primero o el último. El valor del vino
     * se multiplica por el año corriente
     * @param i representa el elemento de más a la izquierda
     * @param j representa el elemento de más a la derecha
     * @param k representa el año corriente
     * @return el maximo de vender todos los vinos usando la tecnica Greedy
     */
    public int calcularMaxValor(int i, int j, int k){
        if (valoresVinos.length == 0) return 0; // Caso particular
        if (i >j) return 0; // Caso base
        int valorIzq = valoresVinos[i]*k;
        int valorDer = valoresVinos[j]*k;
       int mejorParcialmente = Math.min(valorIzq,valorDer); // Siempre elige el de menor valor, para maximizar los vinos más caros
       if(valorIzq>valorDer){
           return mejorParcialmente + calcularMaxValor(i,j-1,k+1);
       }
       else{
           return mejorParcialmente + calcularMaxValor(i+1,j,k+1);
       }

    }

    /**
     * Forma alternativa a resolver el problema
     * @param i representa el elemento de más a la izquierda
     * @param j representa el elemento de más a la derecha
     * @param k representa el año corriente
     * @return el maximo de vender todos los vinos usando la tecnica Greedy
     */
    public int calcularMaxValorv2(int i, int j, int k){
        if (valoresVinos.length == 0) return 0;
        if (i >j) return 0;
        int valorIzq = valoresVinos[i]*k;
        int valorDer = valoresVinos[j]*k;
        int mejorParcialmente = Math.max(valorIzq,valorDer);
        if(valorIzq>valorDer){
            return mejorParcialmente + calcularMaxValorv2(i+1,j,k+1);
        }
        else{
            return mejorParcialmente + calcularMaxValorv2(i,j-1,k+1);
        }

    }

    // Programa main de pruebas
    public static void main(String[] args) {
        int[] array = new int[]{3,5,7,2,6,32,43,1,2,324,34,32,322,31,21,32,4,3,32,4,32,2,4,34,1,2,4,1323,23,1};
        Vinoteca aux = new Vinoteca(array);
        Vinoteca aux2 = new Vinoteca(array);
        System.out.println("Max valor vinos: " + aux.calcularMaxValor(0,array.length-1,1));
        //System.out.println("Max valor vinos: " + aux2.calcularMaxValorv2(0,array.length-1,1));
    }

}
