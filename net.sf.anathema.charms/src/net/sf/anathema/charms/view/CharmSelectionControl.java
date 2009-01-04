package net.sf.anathema.charms.view;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.model.listener.ListenerList;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.GraphNode;

public final class CharmSelectionControl extends AggregatedDisposable implements ICharmSelectionControl {
  private final ICharmVisuals charmVisuals;
  private final ListenerList<ICharmSelectionListener> selectionListeners = new ListenerList<ICharmSelectionListener>();
  private final IChangeableModel[] models;

  public CharmSelectionControl(ICharmVisuals charmVisuals, IChangeableModel[] models) {
    this.charmVisuals = charmVisuals;
    this.models = models;
    addDisposable(charmVisuals);
  }

  public void connect(final GraphViewer viewer) {
    // TODO Mit Umsetzung von Case 291 die Anmeldung des Selektionslistener wieder herausnehmen
    charmVisuals.connect(this);
    final ISelectable selectable = new ViewerSelectable(viewer);
    final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
      @Override
      public void selectionChanged(final SelectionChangedEvent event) {
        selectionListeners.forAllDo(new GraphSelectionClosure(event, selectable));
        updateVisuals(viewer);
      }
    };
    final IChangeListener changeListener = new IChangeListener() {
      @Override
      public void stateChanged() {
        updateVisuals(viewer);
      }
    };
    updateVisuals(viewer);
    viewer.addSelectionChangedListener(selectionListener);
    for (IChangeableModel model : models) {
      model.addChangeListener(changeListener);
      addDisposable(new ChangeableModelDisposable(model, changeListener));
    }
    addDisposable(new SelectionListenerDisposable(selectionListener, viewer));
  }

  private void updateVisuals(final GraphViewer viewer) {
    for (Object node : viewer.getGraphControl().getNodes()) {
      charmVisuals.update(new ZestCharmNode((GraphNode) node));
    }
  }

  public void addSelectionListener(ICharmSelectionListener listener) {
    selectionListeners.add(listener);
  }

  @Override
  public void removeSelectionListener(ICharmSelectionListener selectionListener) {
    selectionListeners.remove(selectionListener);
  }
}