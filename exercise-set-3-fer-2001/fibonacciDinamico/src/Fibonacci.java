/*
Ejercicio 3: Implemente en Java el algoritmo basado en Programacion Dinamica para calcular el n-esimo numero
de Fibonacci.
* */
public class Fibonacci {

    private int numero;


    /**
     * Funcion que calcula el numero fibonacci en una determinada posicion de la secuencia
     * @param n posicion de la secuencia de la cual queremos conocer el valor
     * @return valor de la secuencia de fibonacci en la posicion n
     */
    private static Integer fibonacci(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        Integer prim=0;
        Integer ult=1;
        Integer aux=0;
        for(int i=2; i<n; i++){
            aux = prim + ult;
            prim = ult;
            ult = aux;
        }
        return ult;
    }

    public static void main(String[] args){
        Integer n = 10;
        System.out.println("El numero en la posicion " + n + " de la secuencia de fib es: " + fibonacci(n));
    }
    
}
