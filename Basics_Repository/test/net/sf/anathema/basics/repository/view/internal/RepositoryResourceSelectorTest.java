package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.treecontent.IViewElementProvider;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IResource;
import org.junit.Test;

public class RepositoryResourceSelectorTest {

  @Test
  public void foo() throws Exception {
    IResource resource = EasyMock.createMock(IResource.class);
    IViewElementProvider elementProvider = EasyMock.createMock(IViewElementProvider.class);
    EasyMock.expect(elementProvider.getViewElement(resource)).andReturn(null);
    ISelector selector = EasyMock.createMock(ISelector.class);
    selector.setSelection(null);
    EasyMock.replay(elementProvider, selector);
    new RepositoryResourceSelector(elementProvider, selector).setSelection(resource);
    EasyMock.verify(selector);
  }
}