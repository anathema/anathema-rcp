package net.sf.anathema.basics.importexport.filestatus;

import java.io.File;

import net.sf.anathema.basics.importexport.Messages;

public class FileExistsStatus implements ISmartFileSelectionDialogStatus {

  @Override
  public boolean isActiveFor(File file) {
    return file.exists();
  }

  @Override
  public boolean isComplete() {
    return true;
  }

  @Override
  public String getMessage() {
    return Messages.FileExistsStatus_FileAlreadyExists + " " + Messages.FileSelectionDialogStatus_ExportFileOkayMessage; //$NON-NLS-1$
  }

  @Override
  public int getMessageType() {
    return WARNING;
  }
}