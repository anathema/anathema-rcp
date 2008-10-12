package net.sf.anathema.charms.view;

import net.disy.commons.core.model.listener.ListenerList;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.GraphNode;

public class CharmSelectionControl extends AggregatedDisposable {
  private final ICharmVisuals charmVisuals;
  private final ListenerList<ICharmSelectionListener> selectionListeners = new ListenerList<ICharmSelectionListener>();

  public CharmSelectionControl(ICharmVisuals charmVisuals) {
    this.charmVisuals = charmVisuals;
    addDisposable(charmVisuals);
  }

  public void connect(final GraphViewer viewer, ICharmIdExtractor idExtractor) {
    // TODO Mit Umsetzung von Case 291 die ANmeldung des Selektionslistener wieder herausnehmen
    charmVisuals.connect(this, idExtractor);
    final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
      @Override
      public void selectionChanged(final SelectionChangedEvent event) {
        selectionListeners.forAllDo(new IClosure<ICharmSelectionListener>() {
          @Override
          public void execute(ICharmSelectionListener listener) throws RuntimeException {
            StructuredSelection selection = (StructuredSelection) event.getSelection();
            listener.charmSelected((String) selection.getFirstElement());
          }
        });
        updateVisuals(viewer);
      }
    };
    updateVisuals(viewer);
    viewer.addSelectionChangedListener(selectionListener);
    addDisposable(new SelectionListenerDisposable(selectionListener, viewer));
  }


  private void updateVisuals(final GraphViewer viewer) {
    for (Object node : viewer.getGraphControl().getNodes()) {
      charmVisuals.update((GraphNode) node);
    }
  }

  public void addSelectionListener(ICharmSelectionListener listener) {
    selectionListeners.add(listener);
  }
}