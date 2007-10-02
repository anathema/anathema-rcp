package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.sf.anathema.campaign.core.importwizard.IFileSelectionDialogStatus;

public final class FileSelectionDialogStatus implements IFileSelectionDialogStatus {

  private final String message;
  private final int messageType;
  private final boolean complete;

  public FileSelectionDialogStatus(File file) {
    if (file == null) {
      this.message = Messages.NoteImportMessageProvider_SelectNoteMessage;
      this.messageType = NONE;
      this.complete = false;
    }
    else if (!file.exists()) {
      this.message = Messages.NoteImportMessageProvider_MissingFileMessage;
      this.messageType = ERROR;
      this.complete = false;
    }
    else if (file.isDirectory()) {
      this.message = Messages.NoteImportMessageProvider_FolderMessage;
      this.messageType = ERROR;
      this.complete = false;
    }
    else {
      this.message = Messages.NoteImportMessageProvider_FinishWizardMessage;
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