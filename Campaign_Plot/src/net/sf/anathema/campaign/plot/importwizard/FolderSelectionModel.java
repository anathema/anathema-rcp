package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import net.sf.anathema.campaign.core.importwizard.AbstractFileSelectionModel;
import net.sf.anathema.campaign.core.importwizard.IFileSelectionModel;

import org.eclipse.jface.dialogs.IMessageProvider;

public class FolderSelectionModel extends AbstractFileSelectionModel implements IFileSelectionModel {

  @Override
  protected boolean checkDirectory(File file) {
    return file.isDirectory();
  }

  @Override
  public IMessageProvider getMessage() {
    return new FolderImportMessageProvider(getFile());
  }
}