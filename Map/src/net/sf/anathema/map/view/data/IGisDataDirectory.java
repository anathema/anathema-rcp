package net.sf.anathema.map.view.data;

import java.io.File;

public interface IGisDataDirectory {

  public boolean canWrite();

  public boolean canRead();
  
  public File getDirectory();
}