package de.hhn.it.devtools.apis.pwmanager.exceptions;

/**
 * Implementation of the IllegalMasterPasswordException.
 */
public class IllegalMasterPasswordException extends Exception {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(IllegalMasterPasswordException.class);

  /**
   * Exception if masterpw is wrong.
   */
  public IllegalMasterPasswordException() {
    super("Wrong master password!");
  }

}
