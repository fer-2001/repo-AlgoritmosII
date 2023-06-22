package trapthecat;

import adversarysearch.StateAdversary;
import java.util.LinkedList;

public class TrapTheCatState implements StateAdversary {

  /**
   * Representation of the ScreenBoard.
   */
  private ScreenBoard screenBoard;
  /**
   * Representation of the parent
   */
  private TrapTheCatState parent;

  /**
   * Representation of the type of hexagon for MiniMax algorithm
   * true = Max
   * false = Min
   */
  private boolean type;

  /**
   * Default Constructor class.
   */
  public TrapTheCatState() {
    type = true;
    screenBoard = new ScreenBoard();
    parent = null;
  }

  /**
   * Modified Constructor
   * @param type is the type of the hexagon
   * @param board is the board of the state
   * @param parent is the parent of the state
   */
  public TrapTheCatState(boolean type, ScreenBoard board, TrapTheCatState parent) {
    this.type = type;
    screenBoard = board;
    this.parent = parent;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMax() {
    return type;
  }

  /**
   * {@inheritDoc}
   */
  public StateAdversary getParent() {
    return parent;
  }

  /**
   * Setter of the parent
   * @param parent is the parent of the state
   */
  public void setParent(TrapTheCatState parent) {
    this.parent = parent;
  }
  /**
   * Getter of cat position
   * @return Number of the Hexagon where the cat is located
   */
  public int getCatPosition() {
    return screenBoard.getCatPos();
  }
  /**
   * Gets a list with adjacent at a given position
   * @param position is the hexagon position to get the adjacent
   * @return LinkedList with the numbers of adjacent in it
   */
  public LinkedList getAdjacentPos(int position) {
    return screenBoard.getAdjacent(position);
  }

  /**
   * Mark hexagon in the specified ScreenBoard position, according to the player's turn.
   * @param position is the position of the hexagon.
   * @param value to set in position.
   */
  public void setHexagonValue(int position, HexagonValue value) {
    screenBoard.paint(position, value);
  }

  /**
   * Returns the content of a specific hexagon.
   * @param position is the position of the hexagon.
   * @return the content of a specific hexagon.
   */
  public HexagonValue getHexagonValue(int position) {
    return screenBoard.getHexagonValue(position);
  }

  /**
   * Setter of turn.
   * @param turn indicate player turn to set.
   */
  public void setType(boolean turn) {
    this.type = turn;
  }

  /**
   * Creates and returns a copy of this State.
   * @return a copy of this State.
   */
  public TrapTheCatState clone() {
    ScreenBoard clonBoard = screenBoard.cloneBoard();
    return (new TrapTheCatState(type, clonBoard, this.parent));
  }

  /**
   * Method to know if a position can be painted
   * @param position to be painted
   * @return true if it can be painted, false otherwise
   */
  public boolean isValidToPaint(int position) {
    return getHexagonValue(position) == HexagonValue.FREE;
  }


  /**
   * {@inheritDoc}
   */
  public boolean end() {
    if (isBorder(getCatPosition())) {
      return true;
    }
    return getAdjacentPos(getCatPosition()).size() == 0;
  }

  /**
   * {@inheritDoc}
   */
  public int value() {
    if (end()) {
      if (isBorder(getCatPosition())) {
        return -1000;
      }
      if (getAdjacentPos(getCatPosition()).size() == 0) {
        return 1000;
      }
      if (getAdjacentPos(getCatPosition()).size() > 1) {
        return 1000 - (getAdjacentPos(getCatPosition()).size() - 1) * 150;
      }
    }
    int[] distancesParent = dijkstra((TrapTheCatState) this.getParent());
    return bestMoveCat(distancesParent) + bestBlocked(distancesParent);
  }


  /**
   * Heuristics for the cat's movement
   * @param distancesParent is the array with the distances calculated with Dijkstra
   * @return a value indicating the assessment of a state for a movement of the cat
   */
  private int bestMoveCat(int[]distancesParent) {
    int[] distancesSon = dijkstra(this);
    int borderSon = closestBorder(distancesSon);
    int sonDistance = distancesSon[borderSon];
    int parentDistance = distancesParent[borderSon];
    if (sonDistance < parentDistance) {
      return sonDistance;
    } else {
      if (parentDistance == sonDistance) {
        return 50;
      }
    }
    return 100;
  }
  /**
   * Calculate the shortest distance to all nodes
   * @param state is an instance of the problem
   * @return an array with the distances from an origin node
   */
  private int[] dijkstra(TrapTheCatState state) {
    int numVertex = 122;
    int[] distances = new int[numVertex];
    boolean[] visited = new boolean[numVertex];
    int[] previous = new int[numVertex];
    for (int i = 0; i < numVertex; i++) {
      distances[i] = Integer.MAX_VALUE - 1;
      visited[i] = false;
    }
    distances[state.getCatPosition()] = 0;
    for (int i = 0; i < numVertex; i++) {
      int minVertex = getMinDistanceVertex(distances, visited);
      visited[minVertex] = true;
      LinkedList neighbors = getAdjacentPos(minVertex);
      for (int k = 0; k < neighbors.size(); k++) {
        if (HexagonValue.BLOCK != getHexagonValue((int) neighbors.get(k))) {
          int nextVertex = (int) neighbors.get(k);
          int newDistance = distances[minVertex] + 1;
          if (!visited[nextVertex] && newDistance < distances[nextVertex]) {
            distances[nextVertex] = newDistance;
            previous[nextVertex] = minVertex;
          }
        }
      }
    }
    return distances;
  }

  /**
   * Method to calculate the best blocking that the machine can do in a given state
   * @param distancesParent is the array with the distances calculated with Dijkstra
   * @return a value indicating the assessment of a state for a movement of the machine
   */
  private int bestBlocked(int[] distancesParent) {
    if (isMax()) {
      int catGoTo = closestBorder(distancesParent);
      distancesParent[catGoTo] = 100;
      int catBlock = closestBorder(distancesParent);
      if (getAdjacentPos(catGoTo).contains(getCatPosition())) {
        if (getHexagonValue(catGoTo) == HexagonValue.BLOCK) {
          return 900;
        }
      } else {
        if (getHexagonValue(catBlock) == HexagonValue.BLOCK) {
          return 200;
        }
      }
    }
    return 0;
  }
  /**
   * Determines if a hexagon is a border
   * @param position is the position to check if is a border
   * @return true if position is a border, false otherwise
   */
  private boolean isBorder(int position) {
    if (position < 1 || position > 121) {
      throw new IllegalArgumentException("position is invalid");
    }
    if (position >= 1 && position <= 11) {
      return true;
    }
    if (position >= 111 && position <= 121) {
      return true;
    }
    for (int i = 11; i <= 121; i = i + 11) {
      if (position == i) {
        return true;
      }
    }
    for (int i = 1; i <= 111; i = i + 11) {
      if (position == i) {
        return true;
      }
    }
    return false;
  }
  /**
   * Auxiliary method to calculate the hexagon that is the shortest distance
   * @param distances array with distances from a given origin
   * @param visited hexagons
   * @return the hexagon with the shortest distance
   */
  private static int getMinDistanceVertex(int[] distances, boolean[] visited) {
    int minDistance = Integer.MAX_VALUE;
    int minVertex = -1;
    for (int i = 0; i < distances.length; i++) {
      if (!visited[i] && distances[i] < minDistance) {
        minDistance = distances[i];
        minVertex = i;
      }
    }
    return minVertex;
  }


  /**
   * Auxiliary method to determine the closest border to a given source hexagon
   * @param distances is the array with distances
   * @return the hexagon located on some border with less distance from the origin
   */
  private  int closestBorder(int[] distances) {
    int shortestDist = Integer.MAX_VALUE;
    int posShortestDist = 0;
    for (int i = 1; i <= 121; i++) {
      if (shortestDist > distances[i] && getHexagonValue(i) == HexagonValue.FREE && isBorder(i)) {
        shortestDist = distances[i];
        posShortestDist = i;
      }
    }
    return posShortestDist;
  }


  /**
   * {@inheritDoc}
   */
  public Object ruleApplied() {
    throw new UnsupportedOperationException("method not yet implemented");
  }

  /**
   * Produces string representation of the state.
   * prints Screen from this State, and additional relevant information.
   *
   * @return a string representing the current State.
     For instance, a screenBoard with only two occupied hexagon:
     hexagon number 1 = blocked and hexagon number 13 = the cat
     should be something like this:
     [XXX,002,003,004,005,006,007,008,009,010,011]
       [012, C ,014,015,016,017,018,019,020,021,022]
     [023,024,025,026,027,028,029,030,031,032,033]
       [034,035,036,037,038,039,040,041,042,043,044]
     [045,046,047,048,049,050,051,052,053,054,055]
       [056,057,058,059,060,061,062,063,064,065,066]
     [067,068,069,070,071,072,073,074,075,076,077]
       [078,079,080,081,082,083,084,085,086,087,088]
     [089,090,091,092,093,094,095,096,097,098,099]
       [100,101,102,103,104,105,106,107,108,109,110]
     [111,112,113,114,115,116,117,118,119,120,121]
*/
  @Override
  public String toString() {
    return screenBoard.toString();
  }
}