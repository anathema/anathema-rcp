package net.sf.anathema.basics.importexport.filestatus;

import java.io.File;

public class NullFileStatus implements ISmartFileSelectionDialogStatus {
  
  private final String message;

  public NullFileStatus(String message) {
    this.message = message;
  }
  

  public boolean isActiveFor(File file) {
    return file == null;
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getMessageType() {
    return  NONE;
  }
}