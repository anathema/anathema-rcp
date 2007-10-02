package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import net.sf.anathema.campaign.core.importwizard.IFileSelectionDialogStatus;
import net.sf.anathema.campaign.core.importwizard.IFileSelectionStatusFactory;

public class FolderSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    return new FolderSelectionDialogStatus(file);
  }
}