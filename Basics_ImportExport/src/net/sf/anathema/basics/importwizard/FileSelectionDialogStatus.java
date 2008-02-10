package net.sf.anathema.basics.importwizard;

import java.io.File;

public final class FileSelectionDialogStatus implements IFileSelectionDialogStatus {

  private final String message;
  private final int messageType;
  private final boolean complete;

  public FileSelectionDialogStatus(File file) {
    if (file == null) {
      this.message = Messages.FileSelectionDialogStatus_SelectFileMessage;
      this.messageType = NONE;
      this.complete = false;
    }
    else if (!file.exists()) {
      this.message = Messages.FileSelectionDialogStatus_MissingFileMessage;
      this.messageType = ERROR;
      this.complete = false;
    }
    else if (file.isDirectory()) {
      this.message = Messages.FileSelectionDialogStatus_FolderMessage;
      this.messageType = ERROR;
      this.complete = false;
    }
    else {
      this.message = Messages.FileSelectionDialogStatus_FinishWizardMessage;
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