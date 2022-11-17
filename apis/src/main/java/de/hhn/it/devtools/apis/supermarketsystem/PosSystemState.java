package de.hhn.it.devtools.apis.supermarketsystem;

/**
 * Enum to represent the different states a POS system can be in.
 */
public enum PosSystemState {
  /** The POS System is available. */
  AVAILABLE,
  /** The POS System is busy. */
  BUSY,
  /** The POS System is in an error state. */
  ERROR,
  /** The POS System is out of service. */
  OUTOFSERVICE
}