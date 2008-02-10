package net.sf.anathema.basics.importexport.filestatus;

import java.io.File;

import net.sf.anathema.basics.importexport.Messages;

public class FileNotExistsStatus implements ISmartFileSelectionDialogStatus {

  public boolean isActiveFor(File file) {
    return !file.exists();
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public String getMessage() {
    return Messages.FileSelectionDialogStatus_MissingFileMessage;
  }

  @Override
  public int getMessageType() {
    return  ERROR;
  }
}