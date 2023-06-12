package engine;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinMaxEngine <S extends StateAdversary, P extends StateProblemAdversary<S>> implements EngineAdversary<P,S> {
  private int maxDepth;
  P problem;
  private List<S> path;


  @Override
  public int getMaxDepth() {
    return maxDepth;
  }

  @Override
  public void setMaxDepth(int maxDepth) {
    if(maxDepth<1){
      throw new UnsupportedOperationException("Altura debe ser mayor o igual a 1");
    }
    this.maxDepth = maxDepth;
  }

  @Override
  public P getProblem() {
    return problem;
  }

  @Override
  public void setProblem(P p) {
    if(p == null){
      throw new UnsupportedOperationException("Problem must been different of null");
    }
    this.problem = p;
  }
  // Problema: Aplico la funcion value de StateAdv cuando llego al max level disponible (Checkear si no es hoja, sino aplicar value)
  @Override
  public int computeValue(S state) {
    S current = state;
    if (state.end()) { // Checkeo si el estado pasado no es final
      return state.value();
    } else {
      int depth = getDepth(state) - 1; // Obtengo la altura de ese estado en el arbol de busqueda
      Queue<S> queue = new LinkedList<>(); // Cola para llevar los sucesores
      LinkedList<S> queueAux = new LinkedList<>(); // Cola que contiene todos los elementos de un nivel
      queue.add(state); // state se agrega como estado inicial en este punto
      while (!queue.isEmpty()) {
        if (queueAux.isEmpty()) { // Si no quedan elementos en ese nivel, agregamos todos los del siguiente e incremento i
          queueAux.addAll(queue);
          depth++;
        }
        current = queue.poll();
        if (queueAux.contains(current)) {
          queueAux.poll();
        }
        assert current != null; // Revisa q current no sea null (?
        if (current.end() || queue.isEmpty()) {
          return current.value();
        } else {
          List<S> succs = problem.getSuccessors(state);
          if (depth < maxDepth) { // Checkeo para no encolar más elementos de un cierto nivel
            for (S s : succs) {
                queue.add(s);
              }
            }
          }
        }
      }
    return current.value();
  }

  @Override
  public S computeSuccessor(S state) {
    int depth = getDepth(state)-1;
    List<S> allSucessors = new LinkedList<>();
    S current;
    Queue<S> queue = new LinkedList<>(); // Cola para llevar los sucesores
    LinkedList<S> queueAux = new LinkedList<>(); // Cola que contiene todos los elementos de un nivel
    queue.add(state); // state se agrega como estado inicial en este punto
    while (depth < maxDepth) {
      if (queueAux.isEmpty()) { // Si no quedan elementos en ese nivel, agregamos todos los del siguiente e incremento i
        queueAux.addAll(queue);
        depth++;
      }
      current = queue.poll();
      allSucessors.add(current);
      if (queueAux.contains(current)) {
        queueAux.poll();
      }
      if (depth < maxDepth) { // Checkeo para no encolar más elementos de un cierto nivel
          List<S> succs = problem.getSuccessors(state);
          for (S s : succs) {
            queue.add(s);
          }
      }
    }

    S result = allSucessors.get(0);
    if(state.isMax()){
      int maxValue = computeValue(allSucessors.get(0));
      for(int i=1; i < allSucessors.size(); i++){
        int value = computeValue(allSucessors.get(i));
        if(maxValue < value){
          maxValue = value;
          result = allSucessors.get(i);
        }
      }
      return result;
    }
    else{
      int minValue = computeValue(allSucessors.get(0));
      for(int i=1; i < allSucessors.size(); i++){
        int value = computeValue(allSucessors.get(i));
        if(minValue > value){
          minValue = value;
          result = allSucessors.get(i);
        }
      }
      return result;
    }
  }

  public int minMaxAB(S n, int alfa, int beta, int alturaMax) {
    if (n.end() || alturaMax == maxDepth) {
      return computeValue(n);
    } else {
      List<S> s = problem.getSuccessors(n);
      for (int i = 0; i < s.size() || alfa < beta; i++) {
        S n_k = s.get(i);
        if (n.isMax()) {
          alfa = Math.max(alfa, minMaxAB(n_k, alfa, beta,alturaMax+1));
        } else {
          beta = Math.min(beta, minMaxAB(n_k, alfa, beta,alturaMax+1));
        }
      }

      if (n.isMax()) {
        return alfa;
      } else {
        return beta;
      }
    }
  }

  private int getDepth(S state){
    int altura = -1;
    Queue<S> queue = new LinkedList<>();
    LinkedList<S> queueAux = new LinkedList<>(); // Cola que contiene todos los elementos de un nivel
    LinkedList<S> contenidos = new LinkedList<>();
    queue.add(problem.initialState());
    contenidos.add(problem.initialState());
    while (!queue.isEmpty()) {
      if (queueAux.isEmpty()) { // Si no quedan elementos en ese nivel, agregamos todos los del siguiente e incremento i
        queueAux.addAll(queue);
        altura ++;
      }
      S current = queue.poll();
      if (queueAux.contains(current)) {
        queueAux.poll();
      }
      assert current != null;
      if (current.equals(state)) {
        return altura;
      } else {
        List<S> succs = problem.getSuccessors(current);
        if (altura < maxDepth) { // Checkeo para no encolar más elementos de un cierto nivel
          for (S s: succs) {
            if (!contenidos.contains(s)) { // Control para evitar repetidos
              queue.add(s);
              contenidos.add(s);
            }
          }
        }
      }
    }
    return altura;
  }

  @Override
  public void report() {
    System.out.println("Length of path to state when search finished: " + path.size());
  }
}
