package net.sf.anathema.basics.importwizard.control;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.importwizard.IFileProvider;

import org.eclipse.swt.widgets.Text;

public final class FileDisplayNameUpdater implements IChangeListener {
  private final Text text;
  private final IFileProvider provider;

  public FileDisplayNameUpdater(Text text, IFileProvider provider) {
    this.text = text;
    this.provider = provider;
  }

  @Override
  public void stateChanged() {
    File file = provider.getFile();
    if (new File(text.getText()).equals(file)) {
      return;
    }
    text.setText(file == null ? "" : file.getAbsolutePath()); //$NON-NLS-1$
  }
}