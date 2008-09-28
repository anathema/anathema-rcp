package net.sf.anathema.charms.view;

import net.sf.anathema.charms.CharmContentProvider;
import net.sf.anathema.charms.CharmPrerequisite;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class ZestView extends ViewPart implements net.sf.anathema.lib.ui.IDisposable {
  public static final String ID = "net.sf.anathema.charms.charmview"; //$NON-NLS-1$
  private final AggregatedDisposable disposables = new AggregatedDisposable();

  static class CharmsLabelProvider extends LabelProvider {
    final Image image = Display.getDefault().getSystemImage(SWT.ICON_WARNING);

    @Override
    public Image getImage(Object element) {
      return null;
    }

    @Override
    public String getText(Object element) {
      if (element instanceof CharmPrerequisite) {
        return null;
      }
      return element.toString();
    }

  }

  @Override
  public void createPartControl(Composite parent) {
    final GraphViewer viewer = new GraphViewer(parent, SWT.NONE);
    viewer.setContentProvider(new CharmContentProvider());
    viewer.setLabelProvider(new CharmsLabelProvider());
    viewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
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