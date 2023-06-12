package engine;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class IterativeDeepeningEngine <S extends State, P extends StateProblem<S>> implements Engine<S,P> {
  private P sp;
  private List<S> path;

  public IterativeDeepeningEngine(){
    path = new LinkedList<S>();
  }

  public IterativeDeepeningEngine(P sp){
    path = new LinkedList<S>();
    this.sp = sp;
  }
  public S performSearch() {
  int n = 0;
  S result = null;
  boolean found = false;
  while(!found){
    result = bfs(n);
    if(result != null){
      found = true;
    }
    n++;
  }
  return result;
  }

  public S bfs(int n) {
    int i = -1;
    Queue<S> queue = new LinkedList<S>();
    LinkedList<S> queueAux = new LinkedList<S>(); // Cola que contiene todos los elementos de un nivel
    LinkedList<S> contenidos = new LinkedList<>();
    queue.add(sp.initialState());
    contenidos.add(sp.initialState());
    boolean found = false;
    S goal = null;
    while (!queue.isEmpty() && !found) {
      if (queueAux.isEmpty()) { // Si no quedan elementos en ese nivel, agregamos todos los del siguiente e incremento i
        queueAux.addAll(queue);
        i ++;
      }
      S current = queue.poll();
      if (queueAux.contains(current)) {
        queueAux.poll();
      }
      if (current.isSuccess()) {
        found = true;
        goal = current;
      } else {
        List<S> succs = sp.getSuccessors(current);
        if (i < n) { // Checkeo para no encolar más elementos de un cierto nivel
          for (S s: succs) {
            if (!contenidos.contains(s)) { // Control para evitar repetidos
              queue.add(s);
              contenidos.add(s);
            }
          }
        }
      }
    }
    if (!(goal == null)) {
      S s = goal;
      while (!(s == null)) {
        path.add(0,s);
        s = (S)s.getParent();
      }
    }
    return goal;
  }

  @Override
  public List<S> getPath() {
    return path;
  }

  @Override
  public void report() {
    System.out.println("Length of path to state when search finished: " + path.size());
  }
}
