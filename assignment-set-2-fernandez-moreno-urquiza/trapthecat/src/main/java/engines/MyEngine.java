package engines;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class MyEngine<S extends StateAdversary, P extends StateProblemAdversary<S>> implements EngineAdversary<P, S> {

  /**
   * Internal representation of the StateProblemAdversary.
   */
  private P spa;

  /**
   * Attribute that contains the maximum depth that a Minimax search tree can reach.
   */
  private int maxDepth;

  /**
   * Contains the last state
   */
  private S lastState;

  /**
   * Constructor for class MyEngine.
   * @param spa is the search problem associated with the engine being created.
   * @param maxDepth is the maximum depth that the game tree can reach
   */
  public MyEngine(P spa, int maxDepth) {
    this.spa = spa;
    if (maxDepth < 0) {
      throw new IllegalArgumentException("Depth must be greater or equal than 0!");
    }
    this.maxDepth = maxDepth;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public int getMaxDepth() {
    return maxDepth;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setMaxDepth(int maxDepth) {
    if (maxDepth < 0) {
      throw new IllegalArgumentException("Depth must be greater or equal than 0!");
    }
    this.maxDepth = maxDepth;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public P getProblem() {
    return spa;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setProblem(P p) {
    this.spa = p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int computeValue(S state) {
    int alfa = spa.minValue();
    int beta = spa.maxValue();
    return minMaxAB(state, alfa, beta, 0);
  }

  /**
   * MiniMax search algorithm with alpha beta pruning
   * @param state to which we will calculate its value
   * @param alfa alpha parameter for MiniMax Algorithm
   * @param beta beta parameter for MiniMax Algorithm
   * @param depth is the max depth the search tree will reach
   * @return state value
   */
  private int minMaxAB(S state, int alfa, int beta, int depth) {
    if (state.end() || depth > maxDepth) {
      return state.value();
    } else {
      List<S> succs = spa.getSuccessors(state);
      for (int i = 0; i < succs.size() && alfa < beta; i++) {
        S s = succs.get(i);
        if (state.isMax()) {
          alfa = Math.max(alfa, minMaxAB(s, alfa, beta, depth + 1));
        } else {
          beta = Math.min(beta, minMaxAB(s, alfa, beta, depth + 1));
        }
      }

      if (state.isMax()) {
        return alfa;
      } else {
        return beta;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public S computeSuccessor(S state) {
    if (state.end()) {
      return state;
    }
    List<S> succs = spa.getSuccessors(state);
    int maxVal = spa.minValue();
    int minVal = spa.maxValue();
    S lastState = null;
    for (S s : succs) {
      int val = computeValue(s);
      if (state.isMax()) {
        if (val > maxVal) {
          maxVal = val;
          lastState = s;
        }
      } else {
        if (minVal > val) {
          minVal = val;
          lastState = s;
        }
      }
    }
    return lastState;
  }

  /**
   * Reports information regarding a previously executed search.
   */
  @Override
  public void report() {
    System.out.println("Last state: " + lastState);
  }

}
