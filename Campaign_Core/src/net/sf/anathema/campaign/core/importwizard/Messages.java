package net.sf.anathema.campaign.core.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.core.importwizard.messages"; //$NON-NLS-1$
  public static String AbstractImportWizard_CouldNotOpen;
  public static String AbstractImportWizard_CreateFileError;
  public static String AbstractImportWizard_ErrorUndoing;
  public static String AbstractImportWizard_ImportError;
  public static String AbstractImportWizard_ImportFailed;
  public static String AbstractImportWizard_ImportJobName;
  public static String AbstractImportWizard_ImportSuccessful;
  public static String AbstractImportWizard_WindowTitle;
  public static String FileSelectionWizardPage_FileNameLabel;
  public static String FileSelectionWizardPage_BrowseButtonLabel;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
