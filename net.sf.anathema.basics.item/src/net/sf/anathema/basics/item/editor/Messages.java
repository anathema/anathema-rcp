package net.sf.anathema.basics.item.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.basics.item.editor.messages"; //$NON-NLS-1$

  public static String ErrorMessageEditorInput_Name;

  public static String ErrorMessageEditorInput_Tooltip;

  public static String StyledTextEditor_SaveErrorMessage;
  public static String StyledTextEditor_SaveJobTask;
  public static String StyledTextEditor_SaveJobTitle;
  
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}