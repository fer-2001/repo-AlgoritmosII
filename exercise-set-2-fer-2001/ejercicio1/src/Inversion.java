public class Inversion{
    public static int cantInversiones;

    public Inversion(){
        cantInversiones=0;
    }

    public static void incInversiones(){
        cantInversiones++;
    }


    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void inversiones(T[] array) {
        int inputLength = array.length;
        // Caso base para cortar la recursion (tenemos un unico elemento en este punto)
        if (inputLength < 2) {
            return;
        }
        // Se divide en dos subArreglos del arreglo original
        int midIndex = inputLength / 2;
        T[] leftHalf =  (T []) new Comparable[midIndex];
        T[] rightHalf = (T []) new Comparable[inputLength - midIndex];
        // Carga de valores
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = array[i];
        }
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = array[i];
        }
        // Llamada recursivas hasta llegar a caso base
        inversiones(leftHalf);
        inversiones(rightHalf);

        merge(array, leftHalf, rightHalf);
    }

    public static <T extends Comparable<? super T>> void merge(T[] array, T[] leftHalf, T[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        for(int i=0; i<leftSize;i++){
            // Para cada elemento de la mitad derecha, comparara con los elementos de la mitad izquierda
            for(int j = 0; j<rightSize;j++){
                // Si lo que está en la izquierda es mayor, entonces, se incrementa inversiones
                if (leftHalf[i].compareTo(rightHalf[j]) > 0) {
                    incInversiones();
                }
            }
        }
    }

        public static void main(String[] args) {
            Integer[] array = {8, 2, 3, 4, 1, 5};
            //Integer[] array = {4,2,1};
            inversiones(array);
            System.out.println("Cantidad de Inversiones: " + cantInversiones);
        }

    }
