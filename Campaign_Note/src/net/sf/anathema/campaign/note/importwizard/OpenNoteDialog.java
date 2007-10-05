package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.sf.anathema.basics.swt.file.FileChoosing;
import net.sf.anathema.campaign.core.importwizard.IFileDialog;

import org.eclipse.swt.widgets.Shell;

public class OpenNoteDialog implements IFileDialog {

  @Override
  public File open(Shell shell) {
    return FileChoosing.openNoteFile(null, shell);
  }
}