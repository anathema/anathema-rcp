package net.sf.anathema.charms.view;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.charms.data.CharmContentProvider;
import net.sf.anathema.charms.data.CharmDataProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

public class ZestView extends ViewPart implements net.sf.anathema.lib.ui.IDisposable {
  public static final String ID = "net.sf.anathema.charms.charmview"; //$NON-NLS-1$
  private final CharmSelectionControl selectionControl;
  private final String treeId;

  public ZestView() {
    this(new StandaloneCharmVisuals(), "Solar: Stealth");
  }

  public ZestView(ICharmVisuals charmVisuals, String treeId, IChangeableModel... models) {
    this.treeId = treeId;
    selectionControl = new CharmSelectionControl(charmVisuals, models);
  }

  @Override
  public void createPartControl(Composite parent) {
    CharmContentProvider contentProvider = new CharmContentProvider();
    final GraphViewer viewer = new GraphViewer(parent, SWT.NONE);
    viewer.setLabelProvider(new CharmLabelProvider(new CharmDataProvider()));
    viewer.setContentProvider(contentProvider);
    viewer.setLayoutAlgorithm(new SugiyamaLayoutAlgorithm(contentProvider));
    viewer.setInput(treeId);
    selectionControl.connect(viewer, contentProvider);
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