package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;


/**
 * A class for "doors".
 */
public class Door {


  private Puzzle puzzle;
  private boolean locked;
  private boolean fake;
  private String inspectMessage;


  /**
   * Door open.
   */
  public Door() {
    inspectMessage = "This door is open";
    locked = false;
    fake = false;
  }

  /**
   * Open door.
   *
   * @param item  using item
   * @return door is open
   */
  public boolean unlock(Item item) {
    boolean isSolved = puzzle.setSolved(item.getItemId());
    if (isSolved) {
      locked = false;
      inspectMessage = "This door is open. ";
    }
    return isSolved;
  }

  /**
   * Get Info about the puzzle.
   *
   * @return puzzle
   */
  public String getInspectMessage() {
    String s = inspectMessage;
    if (locked) {
      s = s + puzzle.getDescription();
    }

    return s;
  }

  /**
   * Item needs to be used for the door.
   *
   * @param key get item
   */
  public void setPuzzle(Item key) {
    this.puzzle = new Puzzle(key.getItemId());

    locked = true;
    inspectMessage = "This door is locked.";
  }

  /**
   * Opens the door after puzzle.
   *
   * @return message that varies, depending upon the locked status of the door .
   */
  public String open() {
    String s = inspectMessage;

    if (locked) {
      s = s + ""
              + puzzle.getDescription();
    } else {
      s = s + ""
              + "You open the door.";
    }
    return s;
  }

  public boolean checkIfLocked() {

    return locked;
  }

  public boolean checkIfFake() {

    return fake;
  }

  public void isFake() {

    fake = true;
  }

  public Puzzle getPuzzle() {

    return puzzle;
  }

}
