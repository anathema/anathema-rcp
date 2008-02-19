package net.sf.anathema.basics.pdfexport;

import java.io.File;

import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.eclipse.runtime.StaticProvider;
import net.sf.anathema.basics.importexport.IFileDialog;
import net.sf.anathema.basics.swt.file.FileChoosing;

import org.eclipse.swt.widgets.Shell;

public class PdfExportDialog implements IFileDialog {

  private final File startingDirectory;
  private final IProvider<String> fileNameProvider;

  public PdfExportDialog(File startingDirectory, String suggestedFileName) {
    this(startingDirectory, new StaticProvider<String>(suggestedFileName));
  }

  public PdfExportDialog(File startingDirectory, IProvider<String> fileNameProvider) {
    this.startingDirectory = startingDirectory;
    this.fileNameProvider = fileNameProvider;
  }

  @Override
  public File open(Shell shell) {
    return FileChoosing.savePdfFile(startingDirectory, shell, fileNameProvider.get());
  }
}