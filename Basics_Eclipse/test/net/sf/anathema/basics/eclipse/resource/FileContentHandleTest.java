package net.sf.anathema.basics.eclipse.resource;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.junit.Test;

public class FileContentHandleTest {

  @Test
  public void isAdaptableForMarkerHandle() throws Exception {
    IMarker marker = EasyMock.createMock(IMarker.class);
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.createMarker("markerID")).andReturn(marker); //$NON-NLS-1$
    EasyMock.replay(file);
    FileContentHandle contentHandle = new FileContentHandle(file);
    IMarkerHandle markerHandler = (IMarkerHandle) contentHandle.getAdapter(IMarkerHandle.class);
    assertSame(marker, markerHandler.createMarker("markerID")); //$NON-NLS-1$
  }
}