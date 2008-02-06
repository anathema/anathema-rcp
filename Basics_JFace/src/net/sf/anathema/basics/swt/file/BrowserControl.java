package net.sf.anathema.basics.swt.file;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import net.sf.anathema.basics.eclipse.logging.Logger;

public class BrowserControl {
  private static final String PLUGIN_ID = "net.sf.anathema.basics.jface"; //$NON-NLS-1$

  public static void displayUrl(final URI uri) {
    final boolean windows = WindowsUtilities.isWindows();
    if (windows) {
      try {
        WindowsUtilities.executeMsdosCommand("start \"\" \"" + uri + "\""); //$NON-NLS-1$ //$NON-NLS-2$
      }
      catch (final IOException e) {
        logThrowableWhileFileOpening(e, uri);
      }
      return;
    }
    try {
      Desktop.getDesktop().browse(uri);
    }
    catch (final Exception e) {
      logThrowableWhileFileOpening(e, uri);
    }
  }

  private static void logThrowableWhileFileOpening(final Throwable throwable, URI uri) {
    new Logger(PLUGIN_ID).error("Error opening URI " + uri, throwable);
  }
}