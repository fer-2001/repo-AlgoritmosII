package engine;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirtsEngine<S extends State, P extends StateProblem<S>> implements Engine<S,P> {
  private P sp;
  private List<S> path;

  public DepthFirtsEngine(){
    path = new LinkedList<S>();
  }

  public DepthFirtsEngine(P sp){
    path = new LinkedList<S>();
    this.sp = sp;
  }


  @Override
  public S performSearch() {
    Stack<S> s = new Stack<>();
    LinkedList<S> contenidos = new LinkedList<>();
    contenidos.add(sp.initialState());
    s.add(sp.initialState());
    S goal = null;
    boolean success = false;
    while ( (!s.isEmpty()) && (!success) ) {
      S current = s.peek();
      if (current.isSuccess()) {
        success = true;
        goal = current;
      }
      else{
        List<S> aux = sp.getSuccessors(current);
        if (aux.get(0) == null){
          s.pop();
        }
        else{
          List<S> succs = sp.getSuccessors(current);
          for (S se: succs) {
            if(!contenidos.contains(se)){
              s.push(se);
              contenidos.add(se);
            }
          }
          }
        }
      }
    if (!(goal == null)) {
      S se = goal;
      while (!(se == null)) {
        path.add(0, se);
        se = (S) se.getParent();
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
