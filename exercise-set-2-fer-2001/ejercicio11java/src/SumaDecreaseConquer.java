public class SumaDecreaseConquer {

    /**
     * Metodo para calcular multiplicacion de un numero usando tecnica de Decrease and conquer (Decremento por una constante)
     * @param n numero a multiplicar
     * @param m numero a multiplicar
     * @return multiplicacion entre m y n
     */
    public static int multiplicacion1(int n, int m){
        int result=n;
        for(int i = 1; i<m; i++){
            result += n; // n sumas
        }
        return result;
    }

    /**
     * Metodo para calcular multiplicacion de un numero usando tecnica de Decrease and conquer (Decremento por un factor constante)
     * @param n numero a multiplicar
     * @param m numero a multiplicar
     * @return multiplicacion entre m y n
     */
    public static int multiplicacion2(int n, int m){
        if(n%2==0){
            return n/2 * 2*m; // Dos multiplicaciones y una division
                                // n/2 sumas + 2 sumas (n * m es equivalente a sumar n veces el valor de m)
        }
        else if(n%2==1){
            if(n>1){
                return ((n-1)/2)*2*m; // Dos multiplicaciones y una division
                                     // n-1/2 sumas + 2 sumas (n * m es equivalente a sumar n veces el valor de m)
            }
        }
        return m;
    }

    public static void main(String[] args){
        System.out.println(multiplicacion1(5,7));
        System.out.println(multiplicacion2(5,7));
    }

}
/*

Por lo tanto, se puede concluir que multiplicacion2 es mejor en cuanto a cantidad de sumas realizadas, pero,
teniendo en cuenta que esto es valido para n>4.
Para multiplicacion1 se hacen "n" sumas
Para multiplicacion2 se hacen n/2 + 2 sumas

En relación a una de sus soluciones, considera que es más económico computar n + n o 2 * n?
Justique su respuesta.
Es más economico n+n

*/