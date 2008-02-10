package net.sf.anathema.basics.importwizard.filestatus;

import java.io.File;

import net.sf.anathema.basics.importwizard.Messages;

public class NullFileStatus implements ISmartFileSelectionDialogStatus {

  public boolean isActiveFor(File file) {
    return file == null;
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public String getMessage() {
    return Messages.FileSelectionDialogStatus_SelectFileMessage;
  }

  @Override
  public int getMessageType() {
    return  NONE;
  }
}