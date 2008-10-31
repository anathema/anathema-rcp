package net.sf.anathema.basics.importexport.filestatus;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileSelectionDialogStatus;

public interface ISmartFileSelectionDialogStatus extends IFileSelectionDialogStatus {

  boolean isActiveFor(File file);
}