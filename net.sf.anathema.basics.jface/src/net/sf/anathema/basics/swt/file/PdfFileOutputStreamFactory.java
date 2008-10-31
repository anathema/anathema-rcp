package net.sf.anathema.basics.swt.file;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class PdfFileOutputStreamFactory implements IOutputStreamFactory {

  private final String suggestedFileName;

  public PdfFileOutputStreamFactory(String suggestedFileName) {
    this.suggestedFileName = suggestedFileName;
  }

  public IStreamResult create(Shell shell) throws FileNotFoundException {
    File file = FileChoosing.savePdfFile(null, shell, suggestedFileName);
    if (file == null) {
      return null;
    }
    if (!file.getName().endsWith(FileTypes.PDF_EXTENSION)) {
      file = new File(file.getParent(), file.getName() + FileTypes.PDF_EXTENSION);
    }
    if (file.exists()) {
      boolean confirmed = MessageDialog.openQuestion(
          shell,
          Messages.PdfFileOutputStreamFactory_SaveDialogTitle,
          Messages.PdfFileOutputStreamFactory_SaveDialogMessage);
      if (!confirmed) {
        return null;
      }
    }
    return new FileOutputStreamResult(file);
  }
}