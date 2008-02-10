package net.sf.anathema.basics.importwizard.filestatus;

import net.sf.anathema.basics.importwizard.IFileSelectionDialogStatus;
import net.sf.anathema.basics.importwizard.Messages;

public class FileOkayStatus implements IFileSelectionDialogStatus{

  @Override
  public boolean isComplete() {
    return true;
  }

  @Override
  public String getMessage() {
    return Messages.FileSelectionDialogStatus_FinishWizardMessage;
  }

  @Override
  public int getMessageType() {
    return NONE;
  }
}