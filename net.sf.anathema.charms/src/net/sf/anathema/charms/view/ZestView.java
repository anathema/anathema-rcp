package net.sf.anathema.charms.view;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.charms.data.CharmContentProvider;
import net.sf.anathema.charms.data.CharmDataProvider;
import net.sf.anathema.charms.view.tooltipp.TooltippMouseListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;

public class ZestView extends ViewPart implements net.sf.anathema.lib.ui.IDisposable {
  public static final String ID = "net.sf.anathema.charms.charmview"; //$NON-NLS-1$
  private final CharmSelectionControl selectionControl;
  private final String treeId;

  public ZestView(ICharmVisuals charmVisuals, String treeId, IChangeableModel... models) {
    this.treeId = treeId;
    selectionControl = new CharmSelectionControl(charmVisuals, models);
  }

  @Override
  public void createPartControl(Composite parent) {
    CharmContentProvider contentProvider = new CharmContentProvider();
    GraphViewer viewer = new GraphViewer(parent, SWT.NONE);
    Graph graphControl = viewer.getGraphControl();
    graphControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    graphControl.addMouseMoveListener(new TooltippMouseListener(viewer));
    viewer.setLabelProvider(new CharmLabelProvider(new CharmDataProvider()));
    viewer.setContentProvider(contentProvider);
    viewer.setLayoutAlgorithm(new SugiyamaLayoutAlgorithm());
    viewer.setInput(treeId);
    selectionControl.connect(viewer);
  }

  public void addSelectionListener(ICharmSelectionListener listener) {
    selectionControl.addSelectionListener(listener);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }

  @Override
  public void dispose() {
    selectionControl.dispose();
    super.dispose();
  }
}