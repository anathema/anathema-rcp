package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.sf.anathema.basics.importwizard.IFileSelectionDialogStatus;
import net.sf.anathema.basics.importwizard.IFileSelectionStatusFactory;

public class FileSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    return new FileSelectionDialogStatus(file);
  }
}