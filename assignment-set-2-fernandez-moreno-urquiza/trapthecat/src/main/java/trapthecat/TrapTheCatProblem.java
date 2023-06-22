package trapthecat;

import adversarysearch.StateProblemAdversary;
import java.util.List;
import java.util.LinkedList;

public class TrapTheCatProblem implements StateProblemAdversary<TrapTheCatState> {

  /**
   * {@inheritDoc}
   */
  public TrapTheCatState initialState() {
    return new  TrapTheCatState();
  }

  /**
   * {@inheritDoc}
   */
  public List<TrapTheCatState> getSuccessors(TrapTheCatState s) {
    if (!(s.isMax())) {
      int catPos = s.getCatPosition();
      LinkedList adjacent = new LinkedList((s.getAdjacentPos(catPos)));
      LinkedList<TrapTheCatState> list = new LinkedList<>();
      adjacent.removeFirst();
      while (!(adjacent.isEmpty())) {
        int position = (int) adjacent.poll();
        TrapTheCatState state = s.clone();
        state.setHexagonValue(position, HexagonValue.CAT);
        state.setParent(s);
        list.add(state);
      }
      return list;
    } else {
      LinkedList<TrapTheCatState> list = new LinkedList<>();
      for (int i = 1; i < 122; i++) {
        TrapTheCatState state = s.clone();
        if (state.getHexagonValue(i) != HexagonValue.BLOCK) {
          state.setHexagonValue(i, HexagonValue.BLOCK);
          state.setParent(s);
          list.add(state);
        }
      }
      return list;
    }
  }

  /**
  * {@inheritDoc}
  */
  public int minValue() {
    return -1000;
  }

  /**
  * {@inheritDoc}
  */
  public int maxValue() {
    return 1000;
  }
}
