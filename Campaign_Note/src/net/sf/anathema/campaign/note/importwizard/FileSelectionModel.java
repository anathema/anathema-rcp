package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.jface.dialogs.IMessageProvider;

public class FileSelectionModel implements IFileSelectionModel {

  private static final String LAUNCH_PATH = new File("").getAbsolutePath(); //$NON-NLS-1$
  private final ChangeControl control = new ChangeControl();
  private File file;

  public boolean isComplete() {
    return file != null && file.exists() && !file.isDirectory();
  }

  @Override
  public void setFile(String filename) {
    setFile(new File(filename));
  }

  @Override
  public void setFile(File newFile) {
    if (newFile == null || newFile.equals(file)) {
      return;
    }
    this.file = newFile;
    control.fireChangedEvent();
  }

  public void addChangeListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  public IMessageProvider getMessage() {
    return new IMessageProvider() {
      @Override
      public String getMessage() {
        if (file == null) {
          return "Please select a Note to import.";
        }
        if (!file.exists()) {
          return "The specified file does not exist.";
        }
        if (file.isDirectory()) {
          return "You have specified a folder.";
        }
        return "Click 'Finish' to import the Note.";
      }

      @Override
      public int getMessageType() {
        if (file == null) {
          return NONE;
        }
        if (!file.exists()) {
          return ERROR;
        }
        if (file.isDirectory()) {
          return ERROR;
        }
        return NONE;
      }
    };
  }

  public File getFile() {
    return file;
  }
}