package net.sf.anathema.campaign.note;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.note.messages"; //$NON-NLS-1$
  public static String NewNoteActionDelegate_NoteCreationError;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
