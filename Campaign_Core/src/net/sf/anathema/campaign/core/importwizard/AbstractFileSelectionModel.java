package net.sf.anathema.campaign.core.importwizard;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.jface.dialogs.IMessageProvider;

public abstract class AbstractFileSelectionModel implements IFileSelectionModel {
  private final ChangeControl control = new ChangeControl();
  private File file;

  public abstract IMessageProvider getMessage();

  protected abstract boolean checkDirectory(File currentFile);

  public boolean isComplete() {
    return file != null && file.exists() && checkDirectory(file);
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

  public File getFile() {
    return file;
  }
}