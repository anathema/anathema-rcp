package net.sf.anathema.charms.view;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

public final class GraphSelectionClosure implements IClosure<ICharmSelectionListener> {
  private final SelectionChangedEvent event;
  private final ISelectable selectable;

  public GraphSelectionClosure(SelectionChangedEvent event, ISelectable selectable) {
    this.event = event;
    this.selectable = selectable;
  }

  @Override
  public void execute(ICharmSelectionListener listener) throws RuntimeException {
    StructuredSelection selection = (StructuredSelection) event.getSelection();
    listener.charmSelected(((ICharmId) selection.getFirstElement()).getId());
    selectable.clearSelection();
  }
}