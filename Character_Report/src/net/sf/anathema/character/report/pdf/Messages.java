package net.sf.anathema.character.report.pdf;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.character.report.pdf.messages"; //$NON-NLS-1$
  public static String CharacterReportRunner_CharacterPdfErrorMessage;
  public static String CharacterReportRunner_FileInUseMessage;
  public static String CharacterReportRunner_Title;
  public static String CharacterReportRunnable_MainTaskSheet;
  public static String CharacterReportRunnable_SubTaskCreateCharacter;
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}