package net.sf.anathema.map.view.data;


public class LayerCreationException extends GisException {

  public LayerCreationException() {
    super();
  }

  public LayerCreationException(String message) {
    super(message);
  }

  public LayerCreationException(String message, Throwable cause) {
    super(message, cause);
  }

  public LayerCreationException(Throwable cause) {
    super(cause);
  }
}