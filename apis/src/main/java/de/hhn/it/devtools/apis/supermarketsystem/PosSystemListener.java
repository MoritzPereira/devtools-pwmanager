package de.hhn.it.devtools.apis.supermarketsystem;

/**
 * Callback to notify observers about a state change of a POS system.
 */
public interface PosSystemListener {
  /**
   *  Informs the listener that the POS System has changed its state.
   *
   * @param state actual state of the POS System
   */
  void newState(PosSystemState state);
}