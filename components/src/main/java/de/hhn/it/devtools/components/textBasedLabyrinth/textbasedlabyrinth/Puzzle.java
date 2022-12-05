package de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth;

/**
 * Puzzle.
 */
public class Puzzle {


  //private final int puzzleId;
  private boolean isSolved;
  private boolean isBeingWorkedOn;
  private int keyId;
  private String description;
  private String unlockMessage;
  private String lockedMessage;

  /**
   * Gets a puzzle or an item to solve the puzzle.
   *
   * @param keyId is the id of the fitting key.
   */
  public Puzzle(int keyId) {
    this.keyId = keyId;
    isSolved = false;
    isBeingWorkedOn = false;
    description = "The door seems locked. A key is needed.";
    unlockMessage = "The key turns, the lock clicks. It is open.";
    lockedMessage = "The door does not unlock.";
  }


  public boolean checkIfSolved() {

    return isSolved;
  }

  public String getDescription() {

    return description;
  }

  /**
   * Sets the puzzle to solved after it is done right.
   *
   * @param keyId the keyId of the Item that is attempting to solve the puzzle.
   * @return puzzle is solved right.
   */
  public boolean setSolved(int keyId) {
    if (this.keyId == keyId) {
      isSolved = true;
    }
    return isSolved;
  }

  public String getUnlockMessage() {
    return unlockMessage;
  }

  public String getLockedMessage() {
    return lockedMessage;
  }
}
