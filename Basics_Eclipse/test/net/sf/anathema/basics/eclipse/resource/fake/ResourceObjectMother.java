package net.sf.anathema.basics.eclipse.resource.fake;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
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

  public static IResource createExistingResource() {
    IFile resource = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(resource.exists()).andStubReturn(true);
    EasyMock.replay(resource);
    return resource;
  }
}