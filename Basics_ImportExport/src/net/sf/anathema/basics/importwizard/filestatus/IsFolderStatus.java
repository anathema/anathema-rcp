package net.sf.anathema.basics.importwizard.filestatus;

import java.io.File;

import net.sf.anathema.basics.importwizard.Messages;

public class IsFolderStatus implements ISmartFileSelectionDialogStatus {

  public boolean isActiveFor(File file) {
    return file.isDirectory();
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public String getMessage() {
    return Messages.FileSelectionDialogStatus_FolderMessage;
  }

  @Override
  public int getMessageType() {
    return  ERROR;
  }
}