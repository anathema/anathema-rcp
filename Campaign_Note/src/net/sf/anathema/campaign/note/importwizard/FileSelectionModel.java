package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.sf.anathema.campaign.core.importwizard.AbstractFileSelectionModel;

import org.eclipse.jface.dialogs.IMessageProvider;

public class FileSelectionModel extends AbstractFileSelectionModel {

  @Override
  protected boolean checkDirectory(File file) {
    return !file.isDirectory();
  }

  @Override
  public IMessageProvider getMessage() {
    return new FileImportMessageProvider(getFile());
  }
}