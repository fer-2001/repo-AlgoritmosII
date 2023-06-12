package engine;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;
import java.util.LinkedList;
import java.util.List;

/**
 * Title:        MinMaxEngine
 * Description:  Class MinMaxEngine implements a Min-Max search
 strategy which can be used with any instance of StateProblemAdversary.
 * @author Giovanni Buchieri
 */

public class MinMaxAlfaPodaEngine<P extends StateProblemAdversary<S>, S extends StateAdversary> implements EngineAdversary<P,S>  {
  /**
   * Internal representation of the StateProblem.
   */
  private P p;

  private int maxDeph;

  private List<S> path;


  public MinMaxAlfaPodaEngine(P p) {
    this.p = p;
    path = new LinkedList<>();
  }

  public MinMaxAlfaPodaEngine(P p, int depth) {
    this.p = p;
    path = new LinkedList<>();
    maxDeph = depth;
  }



  /**
   * Returns the maximum depth to be used for search. This value
   * indicates how many game moves in the future are going to be
   * explored in order to compute the value for a state.
   * @return the maximum depth to be used for search.
   * @ pre. true.
   * @ post. the value of maxDepth is returned.
   */
  public int getMaxDepth(){
    return maxDeph;
  }

  /**
   * Sets the maximum depth to be used for search. This value
   * indicates how many game moves in the future are going to be
   * explored in order to compute the value for a state.
   * @param maxDepth is the value used to set this.maxDepth.
   * @ pre. maxDepth >= 1.
   * @ post. this.maxDepth is set to maxDepth.
   */
  public void setMaxDepth(int maxDepth){
    this.maxDeph = maxDepth;
  }

  /**
   * Returns the problem associated with this search engine.
   * @return a reference to the problem associated with this search engine.
   * @ pre. true.
   * @ post. this.problem is returned.
   */
  public P getProblem(){
    return p;
  }

  /**
   * Sets the problem associated with the search engine.
   * @param p is the search problem to be used for search (to set 'problem' to).
   * @ pre. p!=null.
   * @ post. 'problem' is set to p.
   */
  public void setProblem(P p){
    this.p = p;
  }

  /**
   * Starts the search in order to compute a value for a state. The
   * computation is performed by exploring the game tree corresponding
   * to the problem being analysed, considering state as the root,
   * and with maximum depth maxDepth.
   * @param state is the state for which its value is being computed.
   * @return the value computed for the state.
   * @ pre. problem!=null and state!=null.
   * @ post. the value for the state is computed, via a search in the
  game tree for state as the root, and maxDepth as the maximum
  depth.
   */
  //retorna el valor del estado.
  public int computeValue(S state){
    return state.value();
  }

  private int minMax(S state, int alfa, int beta, int profundidad){
    if (profundidad > maxDeph || state.end()) {
      return computeValue(state);
    }
    if(state.isSuccess()){
      return state.value();
    }
    List<S> succs = p.getSuccessors(state); // Cada vez que entro a min max, tengo q cambiar entre min por max, y viceversa

    for(int i = 0; i < succs.size() && alfa < beta; i ++) {
      //min value y max value calculan cual es el minimo y como contraria el maximo de sus sucesores.
      S s = succs.get(i);

      int value = minMax(s, alfa, beta, profundidad+1);
      if (state.isMax()) {
        alfa = Math.max(alfa, value);
      } else {
        beta = Math.min(beta, value);
      }
    }

    int mejorValor;
    if (state.isMax()) {
      mejorValor = alfa;
    } else {
      mejorValor = beta;

    }

    return mejorValor;
  }

  /**
   * Starts the search in order to compute a most promising successor
   * for a state. The computation is performed by exploring the game
   * tree corresponding to the problem being analysed, considering
   * state as the root, and with maximum depth maxDepth.
   * @param state is the state for which its most promising successor
  is being computed.
   * @return the most promising successor for state. The method
  ruleApplied() in the result indicates which rule led to the
  state.
   * @ pre. problem!=null and state!=null.
   * @ post. the most promising successor for the state is computed,
  via a search in the game tree for state as the root, and
  maxDepth as the maximum depth.
   */
  public S computeSuccessor(S state){
    int alfa = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    if (state.end()) {
      return state;
    }
    List<S> succs = p.getSuccessors(state);
    S mejorSucesor = null;
    int mejorValor = 0;
    for (S s : succs) {
      if (mejorSucesor == null) {
        mejorValor = minMax(s,alfa,beta,0);//minMAx
        mejorSucesor = s;
      } else {
        int aux =  minMax(s,alfa,beta,0); //minMax
        if(s.isMax()){
          if (mejorValor < aux) {
            mejorValor = aux;
            mejorSucesor = s;
          }
        }
        else{
          if (mejorValor > aux) {
            mejorValor = aux;
            mejorSucesor = s;
          }
        }
          //end if
      } //end else
    } //end for



    return mejorSucesor;
  }

  public List<S> getPath() {
    return path;
  }

  /**
   * Reports information regarding a previously executed search.
   * @ pre. computeSuccessor(s) or computeValue(s) have been
  executed and finished.
   * @ post. A report regarding the last performed search is printed
  to standard output.
   */
  public void report() {
    System.out.println("Length of path to state when search finished: " + path.size());
  }
}