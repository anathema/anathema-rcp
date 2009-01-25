package net.sf.anathema.charms.view;

import static org.easymock.EasyMock.*;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.DummyCharmId;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class GraphSelectionClosure_Test {

  private SelectionChangedEvent createEvent(Object... selectionContent) {
    ISelectionProvider source = GenericObjectMother.createDummy(ISelectionProvider.class);
    StructuredSelection selection = new StructuredSelection(selectionContent); //$NON-NLS-1$ //$NON-NLS-2$
    return new SelectionChangedEvent(source, selection);
  }

  @Test
  public void clearsSelectionOnExecution() throws Exception {
    ISelectable selectable = createMock(ISelectable.class);
    selectable.clearSelection();
    replay(selectable);
    SelectionChangedEvent event = createEvent();
    GraphSelectionClosure listener = new GraphSelectionClosure(event, selectable);
    listener.execute(GenericObjectMother.createDummy(ICharmSelectionListener.class));
    verify(selectable);
  }

  @Test
  public void notifiesSelectionListenerOfFirstSelectedCharm() throws Exception {
    ISelectable selectable = GenericObjectMother.createDummy(ISelectable.class);
    SelectionChangedEvent event = createEvent(new DummyCharmId("first"), new DummyCharmId("second"));
    GraphSelectionClosure closure = new GraphSelectionClosure(event, selectable);
    ICharmSelectionListener selectionListener = createMock(ICharmSelectionListener.class);
    selectionListener.charmSelected(new CharmId("first", null)); //$NON-NLS-1$
    replay(selectionListener);
    closure.execute(selectionListener);
    verify(selectionListener);
  }

  @Test
  public void selectsOnlyCharmIds() throws Exception {
    ISelectionProvider source = GenericObjectMother.createDummy(ISelectionProvider.class);
    SelectionChangedEvent event = createEvent(new Object());
    ISelectable selectable = GenericObjectMother.createDummy(ISelectable.class);
    GraphSelectionClosure closure = new GraphSelectionClosure(event, selectable);
    ICharmSelectionListener selectionListener = createMock(ICharmSelectionListener.class);
    replay(selectionListener);
    closure.execute(selectionListener);
    verify(selectionListener);
  }
}