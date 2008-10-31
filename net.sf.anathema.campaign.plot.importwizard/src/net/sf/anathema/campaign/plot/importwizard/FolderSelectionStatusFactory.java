package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileSelectionDialogStatus;
import net.sf.anathema.basics.importexport.IFileSelectionStatusFactory;

public class FolderSelectionStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(File file) {
    // TODO Handle Missing Main File
    // TODO Handle Existing Hierarchy
    return new FolderSelectionDialogStatus(file);
  }
}