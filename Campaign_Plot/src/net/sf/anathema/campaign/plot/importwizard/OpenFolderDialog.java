package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;

import net.sf.anathema.basics.swt.file.FileChoosing;
import net.sf.anathema.campaign.core.importwizard.IFileDialog;

import org.eclipse.swt.widgets.Shell;

public class OpenFolderDialog implements IFileDialog {

  @Override
  public File open(Shell shell) {
    return FileChoosing.openFolder(shell);
  }
}