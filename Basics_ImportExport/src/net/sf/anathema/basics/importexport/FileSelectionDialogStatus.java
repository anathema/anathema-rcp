package net.sf.anathema.basics.importexport;

import java.io.File;

import net.sf.anathema.basics.importexport.filestatus.FileExistsStatus;
import net.sf.anathema.basics.importexport.filestatus.FileNotExistsStatus;
import net.sf.anathema.basics.importexport.filestatus.FileOkayStatus;
import net.sf.anathema.basics.importexport.filestatus.ISmartFileSelectionDialogStatus;
import net.sf.anathema.basics.importexport.filestatus.IsFolderStatus;
import net.sf.anathema.basics.importexport.filestatus.NullFileStatus;

public final class FileSelectionDialogStatus {

  public static IFileSelectionDialogStatus createImportStatus(File file) {
    return create(file, Messages.FileSelectionDialogStatus_FinishWizardMessage, new NullFileStatus(
        Messages.FileSelectionDialogStatus_SelectFileMessage), new FileNotExistsStatus(), new IsFolderStatus(
        Messages.FileSelectionDialogStatus_FolderMessage));
  }

  public static IFileSelectionDialogStatus createExportStatus(File file) {
    return create(file, Messages.FileSelectionDialogStatus_ExportFileOkayMessage, new NullFileStatus(
        Messages.FileSelectionDialogStatus_NoExportFileSelectedMessage), new IsFolderStatus(
        Messages.FileSelectionDialogStatus_ExportFolderSelectedMessage), new FileExistsStatus());
  }

  private static IFileSelectionDialogStatus create(
      File file,
      String finishMessage,
      ISmartFileSelectionDialogStatus... smartFileStatus) {
    for (ISmartFileSelectionDialogStatus status : smartFileStatus) {
      if (status.isActiveFor(file)) {
        return status;
      }
    }
    return new FileOkayStatus(finishMessage);
  }
}