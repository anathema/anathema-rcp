package net.sf.anathema.campaign.core.importwizard;

import java.io.File;

public interface IFileSelectionStatusFactory {

  public IFileSelectionDialogStatus create(File file);
}