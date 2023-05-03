package classifiermachine;

import java.util.LinkedList;
/**
 * Main application.
 *
 */
public class App {
  /**
   * Classify mosquitoes sample.
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    int[] sample = {5, 7, 9, 11, 11, 5, 0, 11, 9, 100, 9};
    System.out.println("There are " + rarest(sample) + " of rarest mosquito");
  }

  /**
   * Return the cardinality of the rarest mosquito type.
   * @param sample the mosquito sample to analyze.
   * @return the cardinality of the rarest mosquito type.
   * @throws IllegalArgumentException if  sample.length &lt 2 or sample.length &gt 2000.
   */
  public static int rarest(int[] sample) {
    if (sample.length < 2 || sample.length > 2000) {
      throw new IllegalArgumentException("Illegal sample length");
    }
    MyMachine example = new MyMachine(sample);
    LinkedList<Integer> indexCambiaMax; //lista para llevar índices en los que el maxRepeat cambia de valor
    //lista de índices de los mosquitos del grupo más frecuente
    LinkedList<Integer> indexGroupMax = new LinkedList<>();
    LinkedList<Integer> eliminados = new LinkedList<>();
    int lengthSampleAux = sample.length - 1;
    int maxRepeat;
    int actualPressButton;


    indexCambiaMax = llenarMaquina(sample, example);
    maxRepeat = example.pressButton();

    if (maxRepeat - 1 == lengthSampleAux) {
      return maxRepeat; //caso que todos sean iguales
    }

    boolean entre;
    int i = 1;
    int k;
    int cambioLast = 0;

    while (maxRepeat - 1 != lengthSampleAux && maxRepeat != 1) { //empiezo el ciclo reduciendo el maxRepeat
      nextIndexChange(sample, eliminados, example, maxRepeat, indexCambiaMax);
      //Saco el último índice donde llegue al máximo de repeticiones
      example.moveOutside(indexCambiaMax.getLast());
      entre = false; // Variable de control entre cada ciclo para comprobar si no hubo cambios
      actualPressButton = example.pressButton();
      //Controlo si maxRepeat cambia luego de eliminar el ultimo indice donde cambia el cardinal
      if (maxRepeat == actualPressButton) {
        entre = true;
        i = 1;
        // Controlo que no cambie la cardinalidad y no exceder el índice donde cambia maxRepeat
        while (maxRepeat == actualPressButton && (sample.length - i > indexCambiaMax.getLast())) {
          // Controlo si el elemento que estoy viendo no fue eliminado
          if (!(eliminados.contains(sample.length - i))) {
            example.moveOutside(sample.length - i);  // Lo saco de la lista
            actualPressButton = example.pressButton(); // Consigo la cardinalidad actual
            cambioLast = sample.length - i; // Almaceno donde fue el último cambio de cardinalidad
          }
          i++;
        } // elimino el elemento con mayor cardinalidad
      }

      indexGroupMax.add(indexCambiaMax.getLast());
      example.moveInside(indexCambiaMax.getLast());
      int j = 1;
      // Mientras la cantidad de mosquitos del grupo de mayor cardinalidad sean distintos del cardinal
      while (indexGroupMax.size() != maxRepeat && indexCambiaMax.getLast() - j >= 0) {
        if (!(eliminados.contains(indexCambiaMax.getLast() - j))) {
          example.moveOutside(indexCambiaMax.getLast() - j);
          if (actualPressButton == example.pressButton()) {
            indexGroupMax.add(indexCambiaMax.getLast() - j);
            example.moveInside(indexCambiaMax.getLast() - j);
          } else {
            if (!(eliminados.contains(indexCambiaMax.getLast() - j))) {
              example.moveInside(indexCambiaMax.getLast() - j);
            }
          }
        }
        j++;
      }
      if (entre) { // Si entró, recupera los elementos que no corresponden al mosquito más repetido
        k = 1;
        while (k <= i && (sample.length - k >= indexCambiaMax.getLast())) {
          if (!(eliminados.contains(sample.length - k))) {
            example.moveInside(sample.length - k);
          }
          k++;
        }
      }
      lengthSampleAux -= indexGroupMax.size(); // Actualiza el tamaño de la muestra
      // Elimina los mosquitos del grupo más grande
      while (!(indexGroupMax.isEmpty())) {
        eliminados.add(indexGroupMax.getFirst());
        example.moveOutside(indexGroupMax.poll());
      }
      // Actualiza el índice donde cambia el max
      if (entre) {
        indexCambiaMax.removeLast();
        indexCambiaMax.addLast(cambioLast);
      } else {
        indexCambiaMax.removeLast();
      }
      // Almacena el próximo cardinal para la siguiente llamada
      maxRepeat = example.pressButton();
    }
    return maxRepeat;
  }

  private static LinkedList<Integer> llenarMaquina(int[] sample, MyMachine example) {
    LinkedList<Integer> indexCambiaMaxAux = new LinkedList<>();
    int actualPressButtonAux;
    int maxRepeatAux = 1;
    for (int i = 0; i <= sample.length - 1; i++) { //LLeno la máquina
      example.moveInside(i); //Meto a la máquina
      actualPressButtonAux = example.pressButton();
      if (maxRepeatAux != actualPressButtonAux) { //si con te ultimo ingreso, cambio el maxRepeat
        maxRepeatAux++; //aumento el maxRepeat
        indexCambiaMaxAux.add(i); //guardo el índice donde maxRepeat cambio
      }
    }
    return  indexCambiaMaxAux; // Retorna la lista de índices (respecto a la muestra) donde cambia el cardinal
  }

  private static void nextIndexChange(int[] sample, LinkedList<Integer> eliminados, MyMachine example,
                                      int maxRepeat, LinkedList<Integer> indexCambiaMax) {
    int actualPressButton = example.pressButton();
    int devolver; // Primer indice donde cambia el MaxRepeat
    // indexCambiaMax está vacía, o eliminados ya contiene el índice
    if ((indexCambiaMax.isEmpty()) || (eliminados.contains(indexCambiaMax.getLast()))) {
      int resta = 0;
      devolver = (sample.length - 1); // Longitud de la muestra
      // Mientras no cambie la cardinalidad, y la diferencia entre
      while (maxRepeat == actualPressButton && (devolver - resta) > 0) {
        // devolver y resta sea positiva
        if (!(eliminados.contains(devolver - resta))) { // Controla si el índice no está ya eliminado
          example.moveOutside(devolver - resta); // Lo saca de la máquina
        }
        resta++; // Incrementa para probar con el siguiente índice
        // Presiona el botón para comprobar el while en la siguiente vuelta
        actualPressButton = example.pressButton();
      } // Sale del while
      devolver = devolver - resta + 1; // Primer índice donde cambio MaxRepeat
      // El primer while controla desde el último índice al primero el índice donde puede cambiar maxRepeat
      int i = 0;
      // Vuelve a meter los elementos eliminados (Considerando siempre a los que no fueron ya eliminados)
      while (i < resta) {
        if (!(eliminados.contains(devolver + i))) {
          example.moveInside(devolver + i);
        }
        i++;
      }
      indexCambiaMax.addLast(devolver); // Agrega al ultimo el indice donde cambió el maxRepeat
    }
  }

}