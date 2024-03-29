package net.sf.anathema.basics.importexport.filestatus;

import net.sf.anathema.basics.importexport.IFileSelectionDialogStatus;

public class FileOkayStatus implements IFileSelectionDialogStatus{

  private final String message;

  public FileOkayStatus(String message) {
    this.message = message;
  }

  @Override
  public boolean isComplete() {
    return true;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getMessageType() {
    return NONE;
  }
}