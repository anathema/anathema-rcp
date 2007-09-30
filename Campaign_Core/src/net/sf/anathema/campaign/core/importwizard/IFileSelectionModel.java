package net.sf.anathema.campaign.core.importwizard;

import java.io.File;

import org.eclipse.jface.dialogs.IMessageProvider;

import net.disy.commons.core.model.listener.IChangeListener;

public interface IFileSelectionModel {

  public boolean isComplete();

  public void setFile(File file);

  public void setFile(String filename);

  public void addChangeListener(IChangeListener changeListener);

  public IMessageProvider getMessage();

  public File getFile();
}