package de.hhn.it.devtools.apis.paintservice;


/**
 * Callback to notify observers about a mode, shape or form change of an action.
 */
public interface PaintListener {


  /**
   *  Informs the listener that the next action has changed its mode.
   *
   * @param action actual Mode of the next drawing action
   */
  void newAction(Action action, Shape shape, int boardId);

}
