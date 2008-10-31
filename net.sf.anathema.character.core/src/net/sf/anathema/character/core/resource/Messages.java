package net.sf.anathema.character.core.resource;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.core.resource.messages"; //$NON-NLS-1$
  public static String ResourceModelMarker_ErrorWhileMarking;
  public static String ModelResourceNameProvider_ModelDisplayNamePattern;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}