package net.sf.anathema.basics.swt.file;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileChoosing {

  private static final String PDF_EXTENSION_NAME = "Portable Document Format (*.pdf)";
  private static final String PDF_EXTENSION = "*.pdf"; //$NON-NLS-1$

  public static File getPdfFile(File startingDirectory, Shell shell) {
    return getFile(startingDirectory, shell, new String[] { PDF_EXTENSION }, new String[] { PDF_EXTENSION_NAME });
  }

  private static File getFile(File startingDirectory, Shell shell, String[] extensions, String[] filterNames) {
    FileDialog dialog = new FileDialog(shell, SWT.SAVE);
    if (startingDirectory != null) {
      dialog.setFileName(startingDirectory.getPath());
    }
    if (extensions != null) {
      dialog.setFilterExtensions(extensions);
      dialog.setFilterNames(filterNames);
    }
    String file = dialog.open();
    if (file != null) {
      file = file.trim();
      if (file.length() > 0) {
        return new File(file);
      }
    }
    return null;
  }
}