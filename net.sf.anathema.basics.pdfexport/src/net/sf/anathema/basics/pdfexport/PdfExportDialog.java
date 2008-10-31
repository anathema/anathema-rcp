package net.sf.anathema.basics.pdfexport;

import java.io.File;

import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.eclipse.runtime.StaticProvider;
import net.sf.anathema.basics.importexport.IFileDialog;
import net.sf.anathema.basics.swt.file.FileChoosing;

import org.eclipse.swt.widgets.Shell;

public class PdfExportDialog implements IFileDialog {

  private final IProvider<String> fileNameProvider;

  public PdfExportDialog(String suggestedFileName) {
    this(new StaticProvider<String>(suggestedFileName));
  }

  public PdfExportDialog(IProvider<String> fileNameProvider) {
    this.fileNameProvider = fileNameProvider;
  }

  @Override
  public File open(Shell shell) {
    return FileChoosing.savePdfFile(null, shell, fileNameProvider.get());
  }
}