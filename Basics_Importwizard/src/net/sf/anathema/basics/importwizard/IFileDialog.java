package net.sf.anathema.basics.importwizard;

import java.io.File;

import org.eclipse.swt.widgets.Shell;

public interface IFileDialog {

  public File open(Shell shell);
}