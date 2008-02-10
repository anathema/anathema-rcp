package net.sf.anathema.basics.importwizard;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileSelectionDialogStatus;
import net.sf.anathema.basics.importexport.IFileSelectionStatusFactory;

public class DummyStatusFactory implements IFileSelectionStatusFactory {

  @Override
  public IFileSelectionDialogStatus create(final File file) {
    return new IFileSelectionDialogStatus() {

      @Override
      public String getMessage() {
        return null;
      }

      @Override
      public int getMessageType() {
        return 0;
      }

      public boolean isComplete() {
        if (file == null) {
          return false;
        }
        else if (!file.exists()) {
          return false;
        }
        else if (file.isDirectory()) {
          return false;
        }
        else {
          return true;
        }
      }
    };
  }
}