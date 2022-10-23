package de.hhn.it.devtools.apis.paintservice;

/**
 * Mode describes the different possible actions on the board
 */
public enum Action {

  /**
   * mode to do drawing actions
   */
  DRAW,

  /**
   * mode to erase single positions
   */
  ERASE,

  /**
   * mode to delete whole drawing actions
   */
  DELETE,


  /**
   * mode to change the color of an earlier drawing action
   */
  COLOR,

  /**
   * mode to change the size od an earlier drawing action
   */
  SIZE

}