import java.util.Arrays;

public class SecComunLarga {

    int[] secuencia;
    int[][] LongSubSecuencia;

    public SecComunLarga(int[] respuesta) {
        this.secuencia = respuesta;
        int n = respuesta.length;
        LongSubSecuencia = new int[n+1][n+1];
        // Iniciazacion para la secuencia vacia
        for(int i = 0; i < n+1; i++){
            LongSubSecuencia[i][0]=0;
        }
        for(int j = 0; j < n+1; j++){
            LongSubSecuencia[0][j]=0;
        }
    }

    private static void mergeSort(int[] array){
        if(array.length < 2){
            return;
        }

        int mitad = array.length/2;

        int [] mitadIzq = new int[mitad];
        int [] mitadDer = new int[array.length-mitad];

        for(int i=0; i<mitad; i++){
            mitadIzq[i] = array[i];
        }
        for(int i=mitad; i< array.length; i++){
            mitadDer[i-mitad] = array[i];
        }

        mergeSort(mitadDer);
        mergeSort(mitadDer);


        merge(array,mitadIzq,mitadDer);

    }

    private static void merge(int[] array, int[] mitadIzq, int[] mitadDer){
        int leftSize = mitadIzq.length;
        int rightSize = mitadDer.length;

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (mitadIzq[i] <= mitadDer[j]) {
                array[k] = mitadIzq[i];
                i++;
            }
            else {
                array[k] = mitadDer[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = mitadIzq[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = mitadDer[j];
            j++;
            k++;
        }
    }

    public void LengthOfLCS(){
        int n = secuencia.length;
        int[] respuestaAlumno = secuencia.clone();
        mergeSort(secuencia);

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(respuestaAlumno[i-1] == secuencia[j-1]){
                    LongSubSecuencia[i][j] = LongSubSecuencia[i-1][j-1] + 1;
                }
                else{
                    LongSubSecuencia[i][j] = Math.max(LongSubSecuencia[i-1][j], LongSubSecuencia[i][j-1]);
                }
            }
        }

        int maxSubSec = LongSubSecuencia[n][n];
        System.out.println("La subsecuencia más larga es: " + maxSubSec);
        System.out.println("Respuesta del alumno: " + Arrays.toString(respuestaAlumno));
        System.out.println("Respuesta correcta: " + Arrays.toString(secuencia));

    }

    public static void main(String[] args){
        int[] respuestaAlumno = new int[] {1,3,2,4};
        SecComunLarga aux = new SecComunLarga(respuestaAlumno);
        aux.LengthOfLCS();
    }
}
