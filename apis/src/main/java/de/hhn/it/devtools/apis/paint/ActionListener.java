package de.hhn.it.devtools.apis.paint;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Callback to notify observers about a mode, shape or form change of an action.
 */
public interface ActionListener {


  /**
   *  Informs the listener that the next action has changed its mode.
   *
   * @param action actual Mode of the next action
   * @param boardId defines on which board the action is valid
   * @throws IllegalParameterException if boardId, action or shape are invalid
   */
  void newAction(Action action, int boardId) throws IllegalParameterException;

}
