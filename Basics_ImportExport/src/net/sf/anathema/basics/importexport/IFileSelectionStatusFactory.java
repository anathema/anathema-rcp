package net.sf.anathema.basics.importexport;

import java.io.File;

public interface IFileSelectionStatusFactory {

  public IFileSelectionDialogStatus create(File file);
}