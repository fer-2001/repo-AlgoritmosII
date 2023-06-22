package trapthecat;

import java.util.Random;
import java.util.LinkedList;

/**
 * ScreenBoard class defines a game board of hexagonal positions and all the methods to access and modify them.
 * In addition, it provides methods to obtain hexagons adjacent to a given one.
 * @author Fernandez Manuel, Urquiza Carlos, Moreno Michael
 */

public class ScreenBoard {


  /**
   * Private attribute save the position of the cat.
   */
  private int catPos;

  /**
   * Private class attribute Adjacency List
   */
  private LinkedList[] board = new LinkedList[122];

  /**
   * ScreenBoard class constructor
   * The adjacency of each position on the board are created considering the particular cases
   */
  @SuppressWarnings({"unchecked"})
  public ScreenBoard() {
    setCatPos(61);
    for (int h = 0; h < 122; h++) {
      board[h] = new LinkedList();
    }

    //Particular cases: 1, 111, 121, 11 (Corners of the board)
    // Position 61 = Initial position of the cat
    board[61].addFirst(HexagonValue.CAT);
    board[1].add(HexagonValue.FREE);
    board[1].add(2);
    board[1].add(12);
    board[111].add(HexagonValue.FREE);
    board[111].add(100);
    board[111].add(112);
    board[11].add(HexagonValue.FREE);
    board[11].add(10);
    board[11].add(20);
    board[11].add(21);
    board[121].add(HexagonValue.FREE);
    board[121].add(120);
    board[121].add(109);
    board[121].add(110);

    // Hexagons positioned to the left with 5 adjacent
    int i = 12;
    while (i <= 100) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 11);
      board[i].add(i - 10);
      board[i].add(i + 1);
      board[i].add(i + 11);
      board[i].add(i + 12);
      i += 22;
    }

    // Hexagons positioned to the right with 5 adjacent
    i = 33;
    while (i <= 99) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 12);
      board[i].add(i - 11);
      board[i].add(i - 1);
      board[i].add(i + 10);
      board[i].add(i + 11);
      i += 22;
    }


  // Hexagons a positioned below with 4 adjacent

  i = 112;
    while (i <= 120) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 1);
      board[i].add(i - 12);
      board[i].add(i - 11);
      board[i].add(i + 1);
      i += 1;
    }

    // Hexagons positioned to the right with 3 adjacent
    i = 22;
    while (i <= 110) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 11);
      board[i].add(i - 1);
      board[i].add(i + 11);
      i += 22;
    }

    // Hexagons positioned on top with 4 adjacent
    i = 2;
    while (i <= 10) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 1);
      board[i].add(i + 11);
      board[i].add(i + 10);
      board[i].add(i + 1);
      i += 1;
    }

    // Hexagons positioned to the left with 3 adjacent
    i = 23;
    while (i <= 89) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 11);
      board[i].add(i + 1);
      board[i].add(i + 11);
      i += 22;
    }

    // Middle hexagons on the board, odd row not counting the first row
    i = 13;
    int j = 0;
    while (i <= 109) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 11);
      board[i].add(i - 10);
      board[i].add(i - 1);
      board[i].add(i + 1);
      board[i].add(i + 11);
      board[i].add(i + 12);
      if (i == 61) {
        board[i].remove(1);
      }
      if (j != 8) {
        i++;
        j++;
      } else {
        j = 0;
        i += 14;
      }
    }

    // Middle hexes on the board, even row, not counting the first row
    i = 24;
    j = 0;
    while (i <= 98) {
      board[i].add(HexagonValue.FREE);
      board[i].add(i - 12);
      board[i].add(i - 11);
      board[i].add(i + 1);
      board[i].add(i - 1);
      board[i].add(i + 10);
      board[i].add(i + 11);
      if (j != 8) {
        i++;
        j++;
      } else {
        j = 0;
        i += 14;
      }
    }
    Random aleatorio = new Random();
    int random = aleatorio.nextInt(15);

    for (int s = 0; s < random; s++) {
      paint(posToPaint(), HexagonValue.BLOCK);
    }
  }

  /**
   * ScreenBoard class constructor
   * @param board ScreenBoard of the new object
   * @param catPos is the cat position
   */
  public ScreenBoard(LinkedList[] board, int catPos) {
    this.board = board;
    this.catPos = catPos;
  }

  /**
   * Returns the adjacent ones of a position
   * @param position position from which we want the adjacent ones.
   * @return a list of adjacent at a given position
   */
  public LinkedList getAdjacent(int position) {
    LinkedList adjacent = new LinkedList(board[position]);
    if (adjacent.size() > 0) {
      adjacent.removeFirst();
    }
    return adjacent;
  }

  /**
   Method that generates a random position between 1 and 121,
   and if that position is FREE,it returns it,
   otherwise it generates another random.
   @return an int representing a position to paint
   */
  private int posToPaint() {
    Random random = new Random();
    while (true) {
      int position = random.nextInt(122);
      if (position != 0 && this.getHexagonValue(position) != HexagonValue.BLOCK
              && this.getHexagonValue(position) != HexagonValue.CAT) {
        return position;
      }
    }
  }

  /**
   * Method that returns the value of Hexagon (FREE, BLOCK, CAT).
   * @param position of which we want to know its value on the board
   * @return The value of the hexagon at a given position
   */
  public HexagonValue getHexagonValue(int position) {
    return (HexagonValue) board[position].getFirst();
  }

  /**
   * Method to return the position of the cat in a particular instance of the problem
   * @return the position of the cat
   */
  public int getCatPos() {
      return catPos;
  }

  /**
   * Method to set the position of the cat in a particular instance of the problem
   * @param position is the new position of cat
   */
  public void setCatPos(int position) {
    catPos = position;
  }

  /**
   * Method to clone a ScreenBoard
   * @return a clone of the ScreenBoard
   */
  public ScreenBoard cloneBoard() {
    LinkedList[] auxBoard = new LinkedList[122];
    for (int i = 0; i < 122; i++) {
      auxBoard[i] = new LinkedList(board[i]);
    }
    return (new ScreenBoard(auxBoard, getCatPos()));
  }

  /**
   * Method to change the value of a hexagon
   * @param position of the hexagon to paint
   * @param value for the hexagon
   */
  public void paint(int position, HexagonValue value) {
    if (board[position].getFirst() == HexagonValue.CAT && value == HexagonValue.FREE) {
      board[position].removeFirst();
      board[position].addFirst(HexagonValue.FREE);
    }
    if (board[position].getFirst() == HexagonValue.BLOCK && value == HexagonValue.FREE) {
      board[position].removeFirst();
      for (int i = 0; i < board[position].size(); i++) {
        board[ (int) board[position].get(i)].add(position);
      }
      board[position].addFirst(HexagonValue.FREE);
    }
    if (board[position].getFirst() == HexagonValue.FREE && (value == HexagonValue.CAT || value == HexagonValue.BLOCK)) {
      board[position].removeFirst();
      if (value == HexagonValue.BLOCK) {
        for (int i = 0; i < board[position].size(); i++) {
          board[ (int) board[position].get(i)].removeFirstOccurrence(position);
        }
      } else {
        board[getCatPos()].removeFirst();
        board[getCatPos()].addFirst(HexagonValue.FREE);
        setCatPos(position);
      }
      board[position].addFirst(value);
    }
  }

  /**
   * Implementation of the toString method of the ScreenBoard
   * @return String that allows us to observe the game board
   */
  @Override
  public String toString() {
      StringBuilder output = new StringBuilder();
      int j = -10;
      int aux = j + 11;
      for (int i = 1; i <= 11; i++) {
        if (i % 2 == 0) {
          output.append("  ");
        }
        output.append("[");
        for (j = aux; j < aux + 11; j++) {
          if (board[j].getFirst() == HexagonValue.BLOCK) {
            output.append("XXX,");
          } else if (board[j].getFirst() == HexagonValue.CAT) {
            output.append("CAT,");
          } else {
            if (j < 10) {
              output.append("00").append(j).append(",");
            } else if (j > 9 && j < 100) {
              output.append("0").append(j).append(",");
            } else {
              output.append(j).append(",");
            }
          }
        }
        output.append("]" + "\n");
        aux = j;
      }
      return output.toString();
   }
}