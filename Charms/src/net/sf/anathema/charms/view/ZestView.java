package net.sf.anathema.charms.view;

import net.sf.anathema.charms.data.CharmContentProvider;
import net.sf.anathema.charms.data.CharmDataProvider;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

public class ZestView extends ViewPart implements net.sf.anathema.lib.ui.IDisposable {
  public static final String ID = "net.sf.anathema.charms.charmview"; //$NON-NLS-1$
  private final AggregatedDisposable disposables = new AggregatedDisposable();

  @Override
  public void createPartControl(Composite parent) {
    final GraphViewer viewer = new GraphViewer(parent, SWT.NONE);
    viewer.setContentProvider(new CharmContentProvider());
    viewer.setLabelProvider(new CharmLabelProvider(new CharmDataProvider()));
    viewer.setLayoutAlgorithm(new SugiyamaLayoutAlgorithm());
    viewer.setInput(new Object());
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