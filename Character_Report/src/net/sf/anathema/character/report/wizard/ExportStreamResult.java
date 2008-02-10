package net.sf.anathema.character.report.wizard;

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
    if (openModel.getValue()) {
      BrowserControl.displayUrl(fileSelectionModel.getFile().toURI());
    }
  }

  @Override
  public OutputStream createStream() throws IOException {
    if (fileSelectionModel.isComplete()) {
      return new FileOutputStream(fileSelectionModel.getFile());
    }
    return null;
  }
}