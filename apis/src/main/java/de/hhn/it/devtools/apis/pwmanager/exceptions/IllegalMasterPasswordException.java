package de.hhn.it.devtools.apis.pwmanager.exceptions;

public class IllegalMasterPasswordException extends Exception {

  public IllegalMasterPasswordException() {
    super("Wrong master password!");
  }

}
