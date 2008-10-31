package net.sf.anathema.rcp;

import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

public class WorkspaceLock {

  private final Location location;

  public WorkspaceLock() {
    this(Platform.getInstanceLocation());
  }

  public WorkspaceLock(Location location) {
    this.location = location;
  }

  public boolean lock() throws IOException {
    return location.lock();
  }

  public void release() {
    location.release();
  }
}