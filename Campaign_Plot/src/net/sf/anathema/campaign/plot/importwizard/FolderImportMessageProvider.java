package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;

public class FolderImportMessageProvider implements IMessageProvider {

  private final String message;
  private final int messageType;

  public FolderImportMessageProvider(File file) {
    if (file == null) {
      this.message = Messages.FolderImportMessageProvider_Select;
      this.messageType = NONE;
    }
    else if (!file.exists()) {
      this.message = Messages.FolderImportMessageProvider_FolderDoesNotExist;
      this.messageType = ERROR;
    }
    else if (!file.isDirectory()) {
      this.message = Messages.FolderImportMessageProvider_FileSelected;
      this.messageType = ERROR;
    }
    else {
      this.message = Messages.FolderImportMessageProvider_ModelCompleted;
      this.messageType = NONE;
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
}