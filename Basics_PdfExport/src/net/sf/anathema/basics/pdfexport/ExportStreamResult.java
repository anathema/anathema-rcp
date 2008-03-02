package net.sf.anathema.basics.pdfexport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.swt.file.BrowserControl;
import net.sf.anathema.basics.swt.file.IStreamResult;

public final class ExportStreamResult implements IStreamResult {
  private final IFileSelectionModel fileSelectionModel;
  private final BooleanModel openModel;

  public ExportStreamResult(IFileSelectionModel fileSelectionModel, BooleanModel openModel) {
    this.fileSelectionModel = fileSelectionModel;
    this.openModel = openModel;
  }

  @Override
  public void openResult() {
    File file = fileSelectionModel.getFile();
    if (openModel.getValue() && file != null) {
      BrowserControl.displayUrl(file.toURI());
    }
  }

  @Override
  public OutputStream createStream() throws IOException {
    if (fileSelectionModel.isComplete()) {
      return new FileOutputStream(fileSelectionModel.getFile());
    }
    return null;
  }

  @Override
  public void deleteResult() {
    File file = fileSelectionModel.getFile();
    if (file != null) {
      file.delete();
    }
  }
}