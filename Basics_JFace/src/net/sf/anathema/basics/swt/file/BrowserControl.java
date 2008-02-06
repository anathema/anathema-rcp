package net.sf.anathema.basics.swt.file;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * A simple, static class to display a URL in the system browser.
 * 
 * Under Unix, the system browser is hard-coded to be 'netscape'. Netscape must
 * be in your PATH for this to work. This has been tested with the following
 * platforms: AIX, HP-UX and Solaris.
 * 
 * Under Windows, this will bring up the default browser under windows, usually
 * either Netscape or Microsoft IE. The default browser is determined by the
 * OS. This has been tested under Windows 95/98/NT.
 * 
 * Examples: BrowserControl.displayURL("http://www.javaworld.com")
 * BrowserControl.displayURL("file://c:\\docs\\index.html")
 * BrowserContorl.displayURL("file:///user/joe/index.html");
 * 
 * Note - you must include the url type -- either "http://" or "file://".
 */
public class BrowserControl {

  public static void displayUrl(final URL url) {
    displayUrl(url.toExternalForm());
  }

  /**
   * Display a file in the system browser. If you want to display a file, you
   * must include the absolute path name.
   * 
   * @param url
   *                the file's url (the url must start with either "http://" or
   *                "file://").
   */
  public static void displayUrl(final String url) {
    final boolean windows = WindowsUtilities.isWindows();
    if (windows) {
      String cmd = null;
      try {
        cmd = WIN_PATH + " " + WIN_FLAG + " \"" + url + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        WindowsUtilities.executeMsdosCommand(cmd);
      }
      catch (final IOException x) {
        // TODO Fehlerhandling
      }
      return;
    }
    try {
      Desktop.getDesktop().browse(new URI(url));
    }
    catch (final Exception e) {
      // TODO Fehlerhandling
    }
  }
  // The default system browser under windows.
  private static final String WIN_PATH = "start"; //$NON-NLS-1$

  // The flag to display a url.
  private static final String WIN_FLAG = "\"\""; //$NON-NLS-1$

}
