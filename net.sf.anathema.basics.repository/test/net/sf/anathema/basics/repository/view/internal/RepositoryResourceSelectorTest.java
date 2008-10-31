package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.treecontent.IViewElementProvider;

import org.easymock.EasyMock;
import org.junit.Test;

public class RepositoryResourceSelectorTest {

  @Test
  public void delegatesSelectionToSelector() throws Exception {
    ILink linkageId = EasyMock.createMock(ILink.class);
    IViewElementProvider elementProvider = EasyMock.createMock(IViewElementProvider.class);
    EasyMock.expect(elementProvider.getViewElement(linkageId)).andReturn(null);
    ISelector selector = EasyMock.createMock(ISelector.class);
    selector.setSelection(null);
    EasyMock.replay(elementProvider, selector);
    new RepositoryResourceSelector(elementProvider, selector).setSelection(linkageId);
    EasyMock.verify(selector);
  }
}