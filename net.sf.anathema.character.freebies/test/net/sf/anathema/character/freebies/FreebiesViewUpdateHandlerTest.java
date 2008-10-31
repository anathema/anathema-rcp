package net.sf.anathema.character.freebies;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.character.freebies.view.FreebiesViewUpdateHandler;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.test.matcher.InstanceOf;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorPart;
import org.junit.Before;
import org.junit.Test;

public class FreebiesViewUpdateHandlerTest {

  private FreebiesViewUpdateHandler updateHandler;
  private IPartContainer partContainer;

  @Before
  public void createUpdateHandler() throws Exception {
    updateHandler = new FreebiesViewUpdateHandler();
  }

  @Before
  public void createPartContainer() throws Exception {
    partContainer = EasyMock.createNiceMock(IPartContainer.class);
  }

  @Test
  public void partListenerIsRegisteredToPartContainer() throws Exception {
    partContainer.addPartListener(EasyMock.isA(TopPartListener.class));
    EasyMock.replay(partContainer);
    updateHandler.init(partContainer, null);
    EasyMock.verify(partContainer);
  }

  @Test
  public void updatableIsCalledWhenEditorPartIsBroughtToTop() throws Exception {
    IUpdatable updatable = EasyMock.createMock(IUpdatable.class);
    updatable.update();
    updatable.update();
    InstanceOf matcher = new InstanceOf(TopPartListener.class);
    EasyMock.reportMatcher(matcher);
    partContainer.addPartListener(null);
    EasyMock.replay(partContainer, updatable);
    updateHandler.init(partContainer, updatable);
    ((TopPartListener) matcher.getLastActual()).partBroughtToTop(EasyMock.createMock(IEditorPart.class));
    EasyMock.verify(updatable);
  }
}