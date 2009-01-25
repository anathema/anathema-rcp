package net.sf.anathema.basics.eclipse.ui;

import static org.easymock.EasyMock.*;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Before;
import org.junit.Test;

public class PartContainer_ListenerTest {

  private PartContainer container;
  private IPartListener listener;
  private IPartService service;

  @Before
  public void createListener() throws Exception {
    listener = createMock(IPartListener.class);
  }

  @Before
  public void create() throws Exception {
    service = createMock(IPartService.class);
    IWorkbenchWindow window = createNiceMock(IWorkbenchWindow.class);
    expect(window.getPartService()).andReturn(service);
    replay(window);
    container = new PartContainer(window);
  }

  @Test
  public void registersListenerWithPartService() throws Exception {
    service.addPartListener(listener);
    replay(service);
    container.addPartListener(listener);
    verify(service);
  }

  @Test
  public void removesListenerFromPartService() throws Exception {
    service.removePartListener(listener);
    replay(service);
    container.removePartListener(listener);
    verify(service);
  }
}