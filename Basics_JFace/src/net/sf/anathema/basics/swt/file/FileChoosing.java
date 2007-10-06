package net.sf.anathema.basics.swt.file;

import static net.sf.anathema.basics.swt.file.FileTypes.*;

import java.io.File;

import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileChoosing {
  private static final String ANY_PDF_FILTER = '*' + PDF_EXTENSION;
  private static final String ANY_NOTE_FILTER = '*' + NOTE_EXTENSION;

  public static File savePdfFile(File startingDirectory, Shell shell, String suggestedFileName) {
    return getFile(startingDirectory, shell, new String[] { ANY_PDF_FILTER }, new String[] { NLS.bind(
        Messages.FileChoosing_PDF_Filter_Description,
        ANY_PDF_FILTER) }, suggestedFileName, SWT.SAVE);
  }

  public static File openNoteFile(File startingDirectory, Shell shell) {
    return getFile(startingDirectory, shell, new String[] { ANY_NOTE_FILTER }, new String[] { NLS.bind(
        Messages.FileChoosing_Note_Filter_Description,
        ANY_NOTE_FILTER) }, null, SWT.OPEN);
  }

  public static File openFolder(Shell shell, String message) {
    DirectoryDialog dialog = new DirectoryDialog(shell);
    if (message != null) {
      dialog.setMessage(message);
    }
    return getFile(dialog.open());
  }

  private static File getFile(
      File startingDirectory,
      Shell shell,
      String[] extensions,
      String[] filterNames,
      String suggestedFileName,
      int style) {
    FileDialog dialog = new FileDialog(shell, style);
    if (startingDirectory != null) {
      dialog.setFilterPath(startingDirectory.getPath());
    }
    if (suggestedFileName != null) {
      dialog.setFileName(suggestedFileName);
    }
    if (extensions != null) {
      dialog.setFilterExtensions(extensions);
      dialog.setFilterNames(filterNames);
    }
    return getFile(dialog.open());
  }

  private static File getFile(String path) {
    if (path != null) {
      path = path.trim();
      if (path.length() > 0) {
        return new File(path);
      }
    }
    return null;
  }
}