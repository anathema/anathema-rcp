package net.sf.anathema.basics.eclipse.resource.fake;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class ResourceObjectMother {

  public static IFile createNonExistingFile() {
    IFile resource = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(resource.exists()).andStubReturn(false);
    EasyMock.replay(resource);
    return resource;
  }

  public static IFile createFile(IContainer parent) {
    IFile file = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(file.getParent()).andStubReturn(parent);
    EasyMock.replay(file);
    return file;
  }

  public static IContainer createContainerWithFileAtArbitraryPath(IFile file) {
    IContainer root = EasyMock.createNiceMock(IContainer.class);
    EasyMock.expect(root.getFile(EasyMock.isA(IPath.class))).andStubReturn(file);
    EasyMock.replay(root);
    return root;
  }

  public static IResource createExistingResource() throws Exception {
    IFile resource = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(resource.exists()).andStubReturn(true);
    EasyMock.expect(resource.findMarkers(EasyMock.isA(String.class), EasyMock.anyBoolean(), EasyMock.anyInt()))
        .andStubReturn(new IMarker[0]);
    EasyMock.expect(resource.createMarker(EasyMock.isA(String.class))).andStubReturn(createMarker());
    EasyMock.replay(resource);
    return resource;
  }

  public static IMarker createMarker() {
    IMarker marker = EasyMock.createNiceMock(IMarker.class);
    EasyMock.replay(marker);
    return marker;
  }

  public static IFile createFileWithCreatedMarker(String markerType, IMarker marker) throws CoreException {
    IFile file = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(file.exists()).andStubReturn(true);
    EasyMock.expect(file.createMarker(markerType)).andReturn(marker);
    EasyMock.replay(file);
    return file;
  }
}