package net.sf.anathema.editor.styledtext;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "net.sf.anathema.editor.styledtext.messages"; //$NON-NLS-1$

  public static String StyledTextEditor_Content;
  public static String StyledTextEditor_Name;

  public static String StyledTextEditorActionBar_BoldButtonName;
  public static String StyledTextEditorActionBar_ItalicsButtonName;
  public static String StyledTextEditorActionBar_UnderlineButtonName;
  
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // nothing to do
  }
}