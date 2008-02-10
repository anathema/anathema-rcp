package net.sf.anathema.basics.importexport;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.importexport.messages"; //$NON-NLS-1$
  public static String AbstractImportWizard_CouldNotOpen;
  public static String AbstractImportWizard_CreateFileError;
  public static String AbstractImportWizard_ErrorUndoing;
  public static String AbstractImportWizard_FailureMessageTitle;
  public static String AbstractImportWizard_ImportError;
  public static String AbstractImportWizard_ImportFailed;
  public static String AbstractImportWizard_ImportJobName;
  public static String AbstractImportWizard_ImportSuccessful;
  public static String AbstractImportWizard_WindowTitle;
  public static String FileSelectionWizardPage_FileNameLabel;
  public static String FileSelectionWizardPage_BrowseButtonLabel;
  public static String FileSelectionDialogStatus_ExportFileOkayMessage;
  public static String FileSelectionDialogStatus_ExportFolderSelectedMessage;
  public static String FileSelectionDialogStatus_FinishWizardMessage;
  public static String FileSelectionDialogStatus_FolderMessage;
  public static String FileSelectionDialogStatus_MissingFileMessage;
  public static String FileSelectionDialogStatus_NoExportFileSelectedMessage;
  public static String FileSelectionDialogStatus_SelectFileMessage;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
