package net.sf.anathema.basics.importwizard;

import java.io.File;

public interface IFileSelectionStatusFactory {

  public IFileSelectionDialogStatus create(File file);
}