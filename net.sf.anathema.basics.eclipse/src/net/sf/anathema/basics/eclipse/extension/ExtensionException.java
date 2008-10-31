package net.sf.anathema.basics.eclipse.extension;

public class ExtensionException extends Exception {

  public ExtensionException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public ExtensionException(String message) {
    super(message);
  }

  public ExtensionException(Throwable throwable) {
    super(throwable);
  }

  public ExtensionException() {
    // nothing to do;
  }
}