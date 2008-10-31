package net.sf.anathema.campaign.note.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.note.importwizard.messages"; //$NON-NLS-1$
  public static String NoteFileSelectionWizardPage_Description;
  public static String NoteFileSelectionWizardPage_OpenNoteLabel;
  public static String NoteFileSelectionWizardPage_PageName;
  public static String NoteImportWizard_ConversionError;

  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
