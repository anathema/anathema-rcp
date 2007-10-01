package net.sf.anathema.basics.swt.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class PdfFileOutputStreamFactory implements IOutputStreamFactory {

  public OutputStream create(Shell shell) throws FileNotFoundException {
    File file = FileChoosing.savePdfFile(null, shell);
    if (file == null) {
      return null;
    }
    if (!file.getName().endsWith(FileTypes.PDF_EXTENSION)) {
      file = new File(file.getParent(), file.getName() + FileTypes.PDF_EXTENSION);
    }
    if (file.exists()) {
      boolean confirmed = MessageDialog.openQuestion(
          shell,
          "Save",
          "The file already exists. Do you want to overwrite it?");
      if (!confirmed) {
        return null;
      }
    }
    return new FileOutputStream(file);
  }
}