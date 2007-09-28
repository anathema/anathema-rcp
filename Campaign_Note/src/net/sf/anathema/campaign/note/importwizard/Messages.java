package net.sf.anathema.campaign.note.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.note.importwizard.messages"; //$NON-NLS-1$
  public static String NoteFileSelectionWizardPage_BrowseButtonLabel;
  public static String NoteFileSelectionWizardPage_Description;
  public static String NoteFileSelectionWizardPage_FileNameLabel;
  public static String NoteFileSelectionWizardPage_OpenNoteLabel;
  public static String NoteFileSelectionWizardPage_PageName;
  public static String NoteImportMessageProvider_FinishWizardMessage;
  public static String NoteImportMessageProvider_FolderMessage;
  public static String NoteImportMessageProvider_MissingFileMessage;
  public static String NoteImportMessageProvider_SelectNoteMessage;
  public static String NoteImportWizard_ErrorMessage;
  public static String NoteImportWizard_WindowTitle;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
