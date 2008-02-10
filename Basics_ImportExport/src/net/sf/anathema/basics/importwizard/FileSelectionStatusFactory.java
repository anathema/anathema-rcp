package net.sf.anathema.basics.importwizard;

import java.io.File;


public class FileSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    return new FileSelectionDialogStatus(file);
  }
}