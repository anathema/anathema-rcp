package net.sf.anathema.map.view.data;

import java.io.File;
import java.io.IOException;

import net.disy.commons.core.util.Ensure;

import org.apache.commons.io.FileUtils;

public class GisDataDirectory implements IGisDataDirectory {

  private File directory;

  public boolean canWrite() {
    return exists() && directory.canWrite();
  }

  public boolean canRead() {
    return exists() && directory.canRead();
  }

  public File getDirectory() {
    return directory;
  }

  private boolean exists() {
    return directory != null && directory.exists();
  }

  public void setDirectory(File directory) {
    String message = "Gisdata directory must be a directory: " + directory.getAbsoluteFile(); //$NON-NLS-1$
    Ensure.ensureArgumentTrue(message, directory.isDirectory());
    this.directory = directory;
    checkDirectory();
  }

  private void checkDirectory() {
    try {
      FileUtils.forceMkdir(directory);
    }
    catch (IOException e) {
      //Nothing to do
    }
    if (!directory.exists()) {
      System.err.println("Gisdata directory does not exist: " + directory.getAbsolutePath()); //$NON-NLS-1$
    }
    else {
      if (!canRead()) {
        System.err.println("Gisdata directory cannot be read: " + directory.getAbsolutePath()); //$NON-NLS-1$
      }
      if (!canWrite())
        System.err.println("Gisdata directory cannot be written: " + directory.getAbsolutePath()); //$NON-NLS-1$
    }
  }
}