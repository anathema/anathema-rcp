package net.sf.anathema.basics.eclipse.extension;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.extension.internal.ExtensionFacade;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.junit.Before;
import org.junit.Test;

public class ExtensionFacadeTest {

  private ExtensionFacade extensionFacade;
  private IExtension extension;

  @Before
  public void createFacade() throws Exception {
    extension = EasyMock.createMock(IExtension.class);
    extensionFacade = new ExtensionFacade(extension);
  }

  @Test
  public void returnsExtensionNameAsContributorId() throws Exception {
    IContributor contributor = EasyMock.createMock(IContributor.class);
    String expectedId = "id"; //$NON-NLS-1$
    EasyMock.expect(contributor.getName()).andReturn(expectedId);
    EasyMock.expect(extension.getContributor()).andReturn(contributor);
    EasyMock.replay(contributor, extension);
    assertEquals(expectedId, extensionFacade.getContributorId());
  }
}
