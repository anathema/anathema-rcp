package net.sf.anathema.charms.view;

import static org.easymock.EasyMock.*;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Before;
import org.junit.Test;

public class GraphSelectionClosure_Test {

  private SelectionChangedEvent event;

  @Before
  public void createEvent() {
    ISelectionProvider source = GenericObjectMother.createDummy(ISelectionProvider.class);
    StructuredSelection selection = new StructuredSelection(new Object[] { "first", "second" }); //$NON-NLS-1$ //$NON-NLS-2$
    event = new SelectionChangedEvent(source, selection);
  }

  @Test
  public void clearsSelectionOnExecution() throws Exception {
    ISelectable mockSelectable = createMock(ISelectable.class);
    mockSelectable.clearSelection();
    replay(mockSelectable);
    GraphSelectionClosure listener = new GraphSelectionClosure(event, mockSelectable);
    listener.execute(GenericObjectMother.createDummy(ICharmSelectionListener.class));
    verify(mockSelectable);
  }

  @Test
  public void notifiesSelectionListenerOfFirstSelectedCharm() throws Exception {
    ISelectable selectable = GenericObjectMother.createDummy(ISelectable.class);
    GraphSelectionClosure listener = new GraphSelectionClosure(event, selectable);
    ICharmSelectionListener selectionListener = createMock(ICharmSelectionListener.class);
    selectionListener.charmSelected("first"); //$NON-NLS-1$
    replay(selectionListener);
    listener.execute(selectionListener);
    verify(selectionListener);
  }
}