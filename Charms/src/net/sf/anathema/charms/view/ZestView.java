package net.sf.anathema.charms.view;

import net.disy.commons.core.model.listener.ListenerList;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.charms.data.CharmContentProvider;
import net.sf.anathema.charms.data.CharmDataProvider;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.GraphNode;

public class ZestView extends ViewPart implements net.sf.anathema.lib.ui.IDisposable {
  public static final String ID = "net.sf.anathema.charms.charmview"; //$NON-NLS-1$
  private final AggregatedDisposable disposables = new AggregatedDisposable();
  private final ICharmVisuals charmVisuals;
  private final ListenerList<ICharmSelectionListener> selectionListeners = new ListenerList<ICharmSelectionListener>();

  public ZestView() {
    this(new StandaloneCharmVisuals());
  }

  public ZestView(ICharmVisuals charmVisuals) {
    this.charmVisuals = charmVisuals;
    disposables.addDisposable(charmVisuals);
  }

  @Override
  public void createPartControl(Composite parent) {
    CharmIdExtractor idExtractor = new CharmIdExtractor();
    final GraphViewer viewer = new GraphViewer(parent, SWT.NONE);
    // TODO Mit Umsetzung von Case 291 wieder herausnehmen
    charmVisuals.connect(this);
    viewer.setLabelProvider(new CharmLabelProvider(new CharmDataProvider()));
    viewer.setContentProvider(new CharmContentProvider());
    viewer.setLayoutAlgorithm(new SugiyamaLayoutAlgorithm(idExtractor));
    viewer.setInput(new Object());
    updateVisuals(viewer);
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
    disposables.addDisposable(new SelectionListenerDisposable(selectionListener, viewer));
    viewer.addSelectionChangedListener(selectionListener);
  }

  public void addSelectionListener(ICharmSelectionListener listener) {
    selectionListeners.add(listener);
  }

  private void updateVisuals(final GraphViewer viewer) {
    for (Object node : viewer.getGraphControl().getNodes()) {
      charmVisuals.update((GraphNode) node);
    }
  }

  @Override
  public void setFocus() {
    // nothing to do
  }

  @Override
  public void dispose() {
    disposables.dispose();
    super.dispose();
  }
}