package net.sf.anathema.rcp;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.osgi.service.datalocation.Location;
import org.junit.Test;

public class WorkspaceLockTest {

  @Test
  public void locksLocationIfUnlocked() throws Exception {
    Location location = EasyMock.createMock(Location.class);
    EasyMock.expect(location.lock()).andReturn(true);
    EasyMock.replay(location);
    WorkspaceLock lock = new WorkspaceLock(location);
    assertTrue(lock.lock());
    EasyMock.verify(location);
  }

  @Test
  public void doesNotLockIfAlreadyLocked() throws Exception {
    Location location = EasyMock.createMock(Location.class);
    EasyMock.expect(location.lock()).andReturn(false);
    EasyMock.replay(location);
    WorkspaceLock lock = new WorkspaceLock(location);
    assertFalse(lock.lock());
    EasyMock.verify(location);
  }

  @Test
  public void releasesLockOnDispose() throws Exception {
    Location location = EasyMock.createMock(Location.class);
    location.release();
    EasyMock.replay(location);
    new WorkspaceLock(location).release();
    EasyMock.verify(location);
  }
}
