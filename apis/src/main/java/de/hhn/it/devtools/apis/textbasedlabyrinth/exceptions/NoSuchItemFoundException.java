package de.hhn.it.devtools.apis.textbasedlabyrinth.exceptions;

/**
 * No Item Found Exception.
 */
public class NoSuchItemFoundException extends Exception {
  //private static final org.slf4j.Logger logger =
  //      org.slf4j.LoggerFactory.getLogger(NoSuchItemFoundException.class);


  public NoSuchItemFoundException() {

  }

  public NoSuchItemFoundException(String message) {
    super(message);
  }
}
