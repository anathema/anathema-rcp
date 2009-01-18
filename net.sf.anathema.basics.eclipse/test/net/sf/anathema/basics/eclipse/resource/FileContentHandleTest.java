package net.sf.anathema.basics.eclipse.resource;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

public class FileContentHandleTest {

  @Test
  public void isAdaptableForMarkerHandle() throws Exception {
    IMarker marker = EasyMock.createMock(IMarker.class);
    String markerId = "markerID"; //$NON-NLS-1$
    IFile file = createMarkedFile(marker, markerId);
    FileContentHandle contentHandle = new FileContentHandle(file);
    IMarkerHandle markerHandler = (IMarkerHandle) contentHandle.getAdapter(IMarkerHandle.class);
    assertSame(marker, markerHandler.createMarker(markerId));
  }

  private IFile createMarkedFile(IMarker marker, String markerId) throws CoreException {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.createMarker(markerId)).andReturn(marker);
    EasyMock.replay(file);
    return file;
  }

  @Test
  public void isAdaptableForResource() throws Exception {
    IFile file = createFile();
    FileContentHandle contentHandle = new FileContentHandle(file);
    assertSame(file, contentHandle.getAdapter(IResource.class));
  }

  private IFile createFile() {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.replay(file);
    return file;
  }
}