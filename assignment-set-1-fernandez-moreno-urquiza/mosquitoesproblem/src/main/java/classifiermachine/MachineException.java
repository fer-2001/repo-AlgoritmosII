package classifiermachine;
/**
*   Custom exception to capture too many calls of operations.
*/
public class MachineException extends RuntimeException {
  /**
   * Default Constructor.
   */
  public MachineException() {
    super();
  }
  /**
   * Constructor with message.
   * @param s exception message.
   */
  public MachineException(String s) {
    super(s);
  }
}
