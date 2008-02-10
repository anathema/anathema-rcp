package net.sf.anathema.basics.importwizard;

import java.io.File;

public class ExportFileSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    return FileSelectionDialogStatus.createExportStatus(file);
  }
}