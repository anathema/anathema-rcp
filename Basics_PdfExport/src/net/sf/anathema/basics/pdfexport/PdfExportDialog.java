package net.sf.anathema.basics.pdfexport;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileDialog;
import net.sf.anathema.basics.swt.file.FileChoosing;

import org.eclipse.swt.widgets.Shell;

public class PdfExportDialog implements IFileDialog {

  private final File startingDirectory;
  private final String suggestedName;

  public PdfExportDialog(File startingDirectory, String suggestedName) {
    this.startingDirectory = startingDirectory;
    this.suggestedName = suggestedName;
  }

  @Override
  public File open(Shell shell) {
    return FileChoosing.savePdfFile(startingDirectory, shell, suggestedName);
  }
}