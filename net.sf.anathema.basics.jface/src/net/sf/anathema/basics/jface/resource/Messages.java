package net.sf.anathema.basics.jface.resource;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.jface.resource.messages"; //$NON-NLS-1$
  public static String MarkerDecoratedImageProvider_ErrorMessageMarkerSearch;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}