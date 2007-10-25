package net.sf.anathema.campaign.plot.importwizard;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.campaign.plot.importwizard.messages"; //$NON-NLS-1$
  public static String FolderImportMessageProvider_FileSelected;
  public static String FolderImportMessageProvider_FolderDoesNotExist;
  public static String FolderImportMessageProvider_ModelCompleted;
  public static String FolderImportMessageProvider_Select;
  public static String LegacyMainFileConverter_ErrorConverting;
  public static String LegacyMainFileConverter_SubtaskExtraction;
  public static String OpenFolderDialog_PlotSelectionMessage;
  public static String PlotImportMessages_OpenLabel;
  public static String PlotImportMessages_PageDescription;
  public static String PlotImportMessages_PageName;
  public static String PlotImportWizard_CopySubTask;
  public static String XSLPlotConverter_FailedToConvertConvert;
  public static String XSLPlotConverter_FailedToConvertHierarchy;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}
