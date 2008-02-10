package net.sf.anathema.basics.importwizard.filestatus;

import java.io.File;

import net.sf.anathema.basics.importwizard.IFileSelectionDialogStatus;

public interface ISmartFileSelectionDialogStatus extends IFileSelectionDialogStatus {

  boolean isActiveFor(File file);
}