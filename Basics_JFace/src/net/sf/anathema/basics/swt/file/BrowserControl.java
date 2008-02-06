package net.sf.anathema.basics.swt.file;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.osgi.util.NLS;

public class BrowserControl {
  private static final String PLUGIN_ID = "net.sf.anathema.basics.jface"; //$NON-NLS-1$

  public static void displayUrl(final URI uri) {
    try {
      Desktop.getDesktop().browse(uri);
    }
    catch (final IOException e) {
      new Logger(PLUGIN_ID).error(NLS.bind(Messages.BrowserControl_ErrorOpeningUriFormat, uri), e);
    }
  }
}