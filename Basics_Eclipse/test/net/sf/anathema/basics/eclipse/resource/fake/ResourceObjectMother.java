package net.sf.anathema.basics.eclipse.resource.fake;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IResource;

public class ResourceObjectMother {

  public static IResource createNonExistingResource() {
    IResource resource = EasyMock.createMock(IResource.class);
    EasyMock.expect(resource.exists()).andReturn(false);
    EasyMock.replay(resource);
    return resource;
  }

}
