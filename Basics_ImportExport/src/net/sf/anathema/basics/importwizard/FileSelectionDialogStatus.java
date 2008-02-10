package net.sf.anathema.basics.importwizard;

import java.io.File;

import net.sf.anathema.basics.importwizard.filestatus.FileNotExistsStatus;
import net.sf.anathema.basics.importwizard.filestatus.FileOkayStatus;
import net.sf.anathema.basics.importwizard.filestatus.ISmartFileSelectionDialogStatus;
import net.sf.anathema.basics.importwizard.filestatus.IsFolderStatus;
import net.sf.anathema.basics.importwizard.filestatus.NullFileStatus;

public final class FileSelectionDialogStatus {

  public static IFileSelectionDialogStatus createImportStatus(File file) {
    return create(file, new NullFileStatus(), new FileNotExistsStatus(), new IsFolderStatus());
  }

  public static IFileSelectionDialogStatus createExportStatus(File file) {
    return create(file, new NullFileStatus(), new IsFolderStatus());
  }

  private static IFileSelectionDialogStatus create(File file, ISmartFileSelectionDialogStatus... smartFileStatus) {
    for (ISmartFileSelectionDialogStatus status : smartFileStatus) {
      if (status.isActiveFor(file)) {
        return status;
      }
    }
    return new FileOkayStatus();
  }
}