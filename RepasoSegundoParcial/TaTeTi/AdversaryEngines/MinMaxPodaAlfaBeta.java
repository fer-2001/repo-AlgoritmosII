package engine.AdversaryEngines;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

/**
* Title:        MinMaxInformed
* Description:  Class MinMaxInformed implements a Best-first search 
                strategy which can be used with any instance of StateProblem.
* @author Edgar Agustin Balestra
*/
public class MinMaxPodaAlfaBeta<S extends StateAdversary, P extends StateProblemAdversary<S>> implements EngineAdversary<P, S> {
 
  /**
   * Internal representation of the StateProblemAdversaty.
   */
  private P spa;
  
  /**
   * 
   */
  private int maxDepth;

  /**
   * 
   */
  private S lastState;

  /** 
   * Constructor for class MinMaxInformed.  
   * @pre. true.
   * @post. Lists path is initialized as empty.
   */
  public MinMaxPodaAlfaBeta() {
    maxDepth = 0;
  }
  
  /** 
   * Constructor for class MinMaxInformed.
   * @param sp is the search problem associated with the engine
     being created.
   * @pre. p!=null.
   * @post. A reference to p is stored in field problem. 
   */  
  public MinMaxPodaAlfaBeta(P spa, int maxDepth) {
    this.spa = spa; 
    this.maxDepth = maxDepth;
  }

  @Override
  public int getMaxDepth() {
    return maxDepth;
  }

  @Override
  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  @Override
  public P getProblem() {
    return spa;
  }

  @Override
  public void setProblem(P p) {
    this.spa = p;
  }

  @Override
  public int computeValue(S state) {
    int alfa = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    return minMaxAB(state, alfa, beta, 0);
  }

  private int minMaxAB(S state, int alfa, int beta, int depth){
    if (state.isSuccess() || state.end() || depth > depth) {
      return state.value();
    } else {
      if (state.isMax()) {
        List<S> succs = spa.getSuccessors(state);
        for (S s : succs) {
          alfa = Math.max(alfa, minMaxAB(s, alfa, beta, depth + 1));
          if (alfa >= beta) {
            break;
          }
        }
        return alfa;
      } else {
        List<S> succs = spa.getSuccessors(state);
        for (S s : succs) {
          beta = Math.min(beta, minMaxAB(s, alfa, beta, depth + 1));
          if (alfa >= beta) {
            break;
          }
        }
        return beta;
      }
    }
  }

  @Override
  public S computeSuccessor(S state) {
    if (state.end() || state.isSuccess()) {
      return state;
    }
    List<S> succs = spa.getSuccessors(state);
    int maxVal = Integer.MIN_VALUE;
    S lastState= null;
    for (S s : succs) {
      int val = computeValue(s);
      if (val > maxVal) {
        maxVal = val;
        lastState = s;
      }
    }
    return lastState;
  }

  /** 
  * Reports information regarding a previously executed search.   
  * @pre. computeSuccessor(s) or computeValue(s) have been 
   executed and finished.
  * @post. A report regarding the last performed search is printed 
    to standard output.
  */
  @Override
  public void report() {
    System.out.println("ultimo estado: "+lastState);
  }
  
}