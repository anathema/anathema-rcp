/**
 * 
 */
package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;

public final class NoteImportMessageProvider implements IMessageProvider {

  private final String message;
  private final int messageType;

  public NoteImportMessageProvider(File file) {
    if (file == null) {
      this.message = Messages.NoteImportMessageProvider_SelectNoteMessage;
      this.messageType = NONE;
    }
    else if (!file.exists()) {
      this.message = Messages.NoteImportMessageProvider_MissingFileMessage;
      this.messageType = ERROR;
    }
    else if (file.isDirectory()) {
      this.message = Messages.NoteImportMessageProvider_FolderMessage;
      this.messageType = ERROR;
    }
    else {
      this.message = Messages.NoteImportMessageProvider_FinishWizardMessage;
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