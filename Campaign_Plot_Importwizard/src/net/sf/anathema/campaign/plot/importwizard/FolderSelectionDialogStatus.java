package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileSelectionDialogStatus;

public class FolderSelectionDialogStatus implements IFileSelectionDialogStatus {

  private final String message;
  private final int messageType;
  private final boolean complete;

  public FolderSelectionDialogStatus(File file) {
    if (file == null) {
      this.message = Messages.FolderImportMessageProvider_Select;
      this.messageType = NONE;
      this.complete = false;
    }
    else if (!file.exists()) {
      this.message = Messages.FolderImportMessageProvider_FolderDoesNotExist;
      this.messageType = ERROR;
      this.complete = false;
    }
    else if (!file.isDirectory()) {
      this.message = Messages.FolderImportMessageProvider_FileSelected;
      this.messageType = ERROR;
      this.complete = false;
    }
    else {
      this.message = Messages.FolderImportMessageProvider_ModelCompleted;
      this.messageType = NONE;
      this.complete = true;
    }
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getMessageType() {
    return messageType;
  }

  public boolean isComplete() {
    return complete;
  }
}