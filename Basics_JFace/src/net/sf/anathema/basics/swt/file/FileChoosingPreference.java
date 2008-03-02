package net.sf.anathema.basics.swt.file;

import java.io.File;
import java.util.prefs.Preferences;

public class FileChoosingPreference implements IDirectoryPreference {

  private static final String CURRENT_DIRECTORY = "currentdirectory"; //$NON-NLS-1$

  public File getDirectory() {
    String directory = getNode().get(CURRENT_DIRECTORY, null);
    if (directory == null) {
      return new File("."); //$NON-NLS-1$
    }
    return new File(directory);
  }

  private Preferences getNode() {
    return Preferences.userRoot().node("anathema/filechoosing"); //$NON-NLS-1$
  }

  public void setDirectory(File file) {
    File directory = file.isDirectory() ? file : file.getParentFile();
    getNode().put(CURRENT_DIRECTORY, directory.getAbsolutePath());
  }
}