package net.sf.anathema.map.view.data;

public class GisException extends Exception {

  public GisException() {
    super();
  }

  public GisException(String message) {
    super(message);
  }

  public GisException(String message, Throwable cause) {
    super(message, cause);
  }

  public GisException(Throwable cause) {
    super(cause);
  }
}