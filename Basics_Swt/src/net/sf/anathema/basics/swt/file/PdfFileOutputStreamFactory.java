package net.sf.anathema.basics.swt.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;


import org.eclipse.swt.widgets.Shell;

public class PdfFileOutputStreamFactory implements IOutputStreamFactory {

  public OutputStream create(Shell shell) throws FileNotFoundException {
    File file = FileChoosing.getPdfFile(null, shell);
    if (file == null) {
      return null;
    }
    return new FileOutputStream(file);
  }
}