package net.sf.anathema.basics.importwizard;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;

import org.eclipse.jface.dialogs.IMessageProvider;

public interface IFileSelectionModel extends IFileProvider {

  public boolean isComplete();

  public void setFile(File file);

  public void setFile(String filename);

  public void addChangeListener(IChangeListener changeListener);

  public IMessageProvider getMessage();
}