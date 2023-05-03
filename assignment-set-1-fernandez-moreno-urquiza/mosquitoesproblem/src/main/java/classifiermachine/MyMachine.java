package classifiermachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * {@code Machine} Implementation.
 */
public class MyMachine implements Machine {
  /**
   * Hashmap que contiene cantidad de mosquitos y tipo "mosquito"
   */
  private HashMap<Integer, Integer> mosquito = new HashMap<>();
  /**
   * Lista que contiene el índice de cada tipo de mosquito en la muestra original "indexInside"
   */
    private ArrayList<Integer> indexInside;
  /**
   * Copia de la muestra de mosquitos dada
   */
    private int[] reference;
  /**
   * Contador de accesos al método moveInside
   */
    private int countInside = 0;
  /**
   * Contador de accesos al método moveOutside
   */
    private int countOutside = 0;
  /**
   * Contador de accesos al método pressButton
   */
    private int countPressButton = 0;

  /**
   * MyMachine constructor.
   * @param sample mosquito sample to analyze.
   */
  public MyMachine(int[] sample) {
      indexInside = new ArrayList<>();
      reference = sample;
  }

    /**
   * {@inheritDoc}
   */
  @Override
  public void setSample(int[] sample) {
      reference = sample;
  }

    /**
   * {@inheritDoc}
   */
  @Override
  public void moveInside(int i) {
      if (countInside >= 40000) {
          throw new MachineException("Number of calls to this procedure exceeds 40000");
      }
      if (i < 0 || i >= reference.length) {
          throw new IllegalArgumentException("Length of the sample is incorrect");
      }
      if (!(indexInside.contains(i))) { // Revisa si el índice ya está contenido en la lista
          int typeMosquito = reference[i];
          indexInside.add(i);
        // Revisa si el tipo de mosquito introducido ya pertenece al Hashmap
          if (mosquito.containsKey(typeMosquito)) {
            int cantActual = mosquito.get(typeMosquito) + 1;
            mosquito.replace(typeMosquito, cantActual);
          } else { // Si no pertenece, agregalo por primera vez
              mosquito.put(typeMosquito, 1);
          }
      }
      countInside++;
  }

    /**
   * {@inheritDoc}
   */
  @Override
  public void moveOutside(int i) {
      if (countOutside >= 40000) {
          throw new MachineException("Number of calls to this procedure exceeds 40000");
      }
      if (i < 0 || i >= reference.length) {
          throw new IllegalArgumentException("Length of the sample is incorrect");
      }

      if (indexInside.contains(i)) {  // Revisa si el índice ya está contenido en la lista

          if (indexInside.size() == 1) {
              indexInside.remove(0);
          } else {
              Object aux = i;
              indexInside.remove(aux);
          }

          int typeMosquito = reference[i];
          // Revisa si el tipo de mosquito introducido ya pertenece al Hashmap
          if (mosquito.containsKey(typeMosquito)) {
              int cantActual = mosquito.get(typeMosquito) - 1;
              mosquito.replace(typeMosquito, cantActual);
              if (mosquito.get(typeMosquito) == 0) {
                  mosquito.remove(typeMosquito);
              }
          }
      }
      countOutside++;

  }

    /**
   * {@inheritDoc}
   */
  @Override
  public int pressButton() {
      if (countPressButton >= 40000) {
        throw new MachineException("Number of calls to this procedure exceeds 40000");
      }
      countPressButton++;
      if (mosquito.isEmpty()) {
        return 0;
      }
      return (Collections.max(mosquito.values()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getOperationCount(MachineOperations operation) {
      switch (operation) {
          case MOVEINSIDE: return countInside;
          case MOVEOUTSIDE: return countOutside;
          case PRESSBUTTON: return countPressButton;
          default:
              return -1;
      }
  }
}
