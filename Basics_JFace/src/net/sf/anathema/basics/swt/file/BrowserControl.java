package net.sf.anathema.basics.swt.file;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.osgi.util.NLS;

public class BrowserControl {
  private static final String PLUGIN_ID = "net.sf.anathema.basics.jface"; //$NON-NLS-1$

  public static void displayUrl(final URI uri) {
    if (WindowsUtilities.isWindows()) {
      browseWindows(uri);
      return;
    }
    browseOthers(uri);
  }

  private static void browseOthers(final URI uri) {
    try {
      Desktop.getDesktop().browse(uri);
    }
    catch (final Exception e) {
      logThrowableWhileFileOpening(e, uri);
    }
  }

  private static void browseWindows(final URI uri) {
    try {
      WindowsUtilities.executeMsdosCommand("start \"\" \"" + uri + "\""); //$NON-NLS-1$ //$NON-NLS-2$
    }
    catch (final IOException e) {
      logThrowableWhileFileOpening(e, uri);
    }
  }

  private static void logThrowableWhileFileOpening(final Throwable throwable, URI uri) {
    new Logger(PLUGIN_ID).error(NLS.bind(Messages.BrowserControl_ErrorOpeningUriFormat, uri), throwable);
  }
}