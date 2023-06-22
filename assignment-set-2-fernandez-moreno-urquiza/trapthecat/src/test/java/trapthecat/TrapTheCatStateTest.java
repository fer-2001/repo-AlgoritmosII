package trapthecat;

import adversarysearch.EngineAdversary;
import engines.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TrapTheCatStateTest {
	
	private static TrapTheCatState initialState = null;
	
	@Before
	public void setUp() {
		TrapTheCatProblem problem = new TrapTheCatProblem();
		initialState = problem.initialState();
	}

	
	@Test
	public void testSetHexagonValueNoChangeParent() {		
		assertTrue("Wrong initial parent", null == initialState.getParent());
		initialState.setHexagonValue(1,HexagonValue.BLOCK);
		assertTrue("Wrong parent", null == initialState.getParent());
	}
	@Test
	public void testSetHexagonValueFREE() {
		initialState.setHexagonValue(61,HexagonValue.FREE);
		assertEquals(HexagonValue.FREE, initialState.getHexagonValue(61));
	}
	
	
	@Test
	public void testSetHexagonValueCAToverCAT() {
		initialState.setHexagonValue(61,HexagonValue.CAT);
		assertEquals(HexagonValue.CAT, initialState.getHexagonValue(61));
	}


	@Test
	public void testOnlyOneCat() {
		initialState.setHexagonValue(2,HexagonValue.CAT);
		int countCats = 0;
		for (int i = 1; i < 122; i++) {
			if (initialState.getHexagonValue(i) == HexagonValue.CAT) {
				countCats++;
			}
		}
		assertEquals(1,countCats);
	}


	/*
	Sonia tests
	*/

	@Test
  public void testEvolveConsistent() {
    TrapTheCatProblem problem = new TrapTheCatProblem();
    TrapTheCatState state = problem.initialState();
    EngineAdversary<TrapTheCatProblem, TrapTheCatState> engine = new MyEngine<>(problem,3);
    while (!state.end()) {
      TrapTheCatState postState = engine.computeSuccessor(state);
      List<Integer> postBlocked = getBlockHexagon(postState);
      List<Integer> preBlocked = getBlockHexagon(state);
      assertTrue("Wrong number of cat in current state ", myOwnCatPosition(state) != -1);
      int catPrePosition = myOwnCatPosition(state);
      assertTrue("Wrong number of cat in successor state ", myOwnCatPosition(postState) != -1);
      int catPostPosition = myOwnCatPosition(postState);
      boolean preInPost = postContainsPreBlockedList(preBlocked,postBlocked);
      if (!state.isMax()) {//state == cat
        postState.setType(true);
        assertEquals("Cat post-State:Wrong number of blocked cells ", preBlocked.size(), postBlocked.size());
        assertTrue("Cat post-State:Unexpected blocked spot ",preInPost);
        assertTrue("Cat post-State: Wrong cat position ", catPrePosition != catPostPosition );
      } else {
        postState.setType(false);
        assertEquals("Player post-State: Wrong number of blocked cells ",  preBlocked.size() + 1, postBlocked.size());
        assertTrue("Player post-State: Unexpected blocked spot ",preInPost);
        assertEquals("Player post-State: Wrong cat position ", catPrePosition, catPostPosition );
        
        

      }
      state = postState;

    }
  }

  /**
   * Get all blocked positions in the param state s.
   * @param s State to know the blocked positions.
   * @return a List with all blocked positions in the param state s.
   */
  private List<Integer> getBlockHexagon(TrapTheCatState s) {
    List<Integer> blocked = new LinkedList<>();
    for (int i = 1; i <= 121; i++) {
      if (s.getHexagonValue(i).equals(HexagonValue.BLOCK)){
        blocked.add(i);
      }
    }
    return blocked;
  }

  /**
   * Compare previous blocked spot respect the actual blocked spot.
   * @param preList Positions of blocked spot before one move.
   * @param postList Positions of blocked spot after one move.
   * @return previous blocked spot respect the actual blocked spot.
   */
  private boolean postContainsPreBlockedList(List<Integer> preList, List<Integer> postList) {
    for (int i = 0; i < preList.size(); i++) {
      if (!postList.contains(preList.get(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * return the cat position if there is only one cat, otherwise return -1.
   * @param state to know the cat position
   * @return the cat position ( 1 : 121) if there is only one cat, otherwise return -1.
   */
  private int myOwnCatPosition(TrapTheCatState state) {
    int catpos = -1;
    int moreThanOneCat = 0;
    for (int i = 1; i <= 121; i++) {
      if (state.getHexagonValue(i).equals(HexagonValue.CAT)) {
        catpos = i;
        moreThanOneCat++;
      }
    }
    if (moreThanOneCat != 1 ) {
      return -1;
    }
    return catpos;
  }




}
