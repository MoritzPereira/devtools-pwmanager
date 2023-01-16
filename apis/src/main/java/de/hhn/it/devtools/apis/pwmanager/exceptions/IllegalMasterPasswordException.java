package de.hhn.it.devtools.apis.pwmanager.exceptions;

public class IllegalMasterPasswordException extends Exception {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(IllegalMasterPasswordException.class);

  public IllegalMasterPasswordException() {
    super("Wrong master password!");
    logger.error("Wrong master password!");
  }

}
