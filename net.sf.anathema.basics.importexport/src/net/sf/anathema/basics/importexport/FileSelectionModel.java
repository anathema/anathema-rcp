package net.sf.anathema.basics.importexport;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.jface.dialogs.IMessageProvider;

public class FileSelectionModel implements IFileSelectionModel {
  private final ChangeControl control = new ChangeControl();
  private final IFileSelectionStatusFactory factory;
  private File file;
  private IFileSelectionDialogStatus status;

  public FileSelectionModel(IFileSelectionStatusFactory factory) {
    this.factory = factory;
    status = factory.create(file);
  }

  @Override
  public void setFile(String filename) {
    if (StringUtilities.isNullOrTrimmedEmpty(filename)) {
      changeCurrentFileTo(null);
    }
    setFile(new File(filename));
  }

  @Override
  public void setFile(File newFile) {
    if (newFile == null || newFile.equals(file)) {
      return;
    }
    changeCurrentFileTo(newFile);
  }

  private void changeCurrentFileTo(File newFile) {
    this.file = newFile;
    this.status = factory.create(file);
    control.fireChangedEvent();
  }

  @Override
  public IMessageProvider getMessage() {
    return status;
  }

  @Override
  public boolean isComplete() {
    return status.isComplete();
  }

  public void addChangeListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  public File getFile() {
    return file;
  }
}