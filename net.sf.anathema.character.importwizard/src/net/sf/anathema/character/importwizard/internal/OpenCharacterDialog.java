package net.sf.anathema.character.importwizard.internal;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileDialog;
import net.sf.anathema.basics.swt.file.FileChoosing;

import org.eclipse.swt.widgets.Shell;

public class OpenCharacterDialog implements IFileDialog {

  @Override
  public File open(Shell shell) {
    return FileChoosing.openCharacterFile(null, shell);
  }
}