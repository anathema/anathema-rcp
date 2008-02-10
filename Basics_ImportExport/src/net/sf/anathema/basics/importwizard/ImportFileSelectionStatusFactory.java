package net.sf.anathema.basics.importwizard;

import java.io.File;


public class ImportFileSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    return FileSelectionDialogStatus.createImportStatus(file);
  }
}