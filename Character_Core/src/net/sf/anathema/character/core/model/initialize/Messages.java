package net.sf.anathema.character.core.model.initialize;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.model.initialize.messages"; //$NON-NLS-1$
  public static String ModelMarkerExtensionPoint_ErrorReadingMarkers;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
