package net.sf.anathema.basics.pdfexport;

import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.basics.swt.file.IStreamResult;

import org.eclipse.swt.widgets.Shell;

public final class ExportOutputStreamFactory implements IOutputStreamFactory {
  private final IFileSelectionModel fileSelectionModel;
  private final BooleanModel openModel;

  public ExportOutputStreamFactory(IFileSelectionModel fileSelectionModel, BooleanModel openModel) {
    this.fileSelectionModel = fileSelectionModel;
    this.openModel = openModel;
  }

  @Override
  public IStreamResult create(Shell shell) throws FileNotFoundException {
    return new ExportStreamResult(fileSelectionModel, openModel);
  }
}