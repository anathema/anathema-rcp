package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotUnitProvider;

import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public final class PlotDragSourceListener extends DragSourceAdapter {
  private final TreeViewer viewer;

  PlotDragSourceListener(TreeViewer viewer) {
    this.viewer = viewer;
  }

  @Override
  public void dragStart(DragSourceEvent event) {
    TreeSelection selection = (TreeSelection) viewer.getSelection();
    event.doit = isOneInnerPlotElementSelected(selection);
  }

  private boolean isOneInnerPlotElementSelected(TreeSelection selection) {
    if (selection.size() != 1) {
      return false;
    }
    Object firstElement = selection.getFirstElement();
    boolean isPlotElementViewElement = firstElement instanceof PlotElementViewElement;
    if (!isPlotElementViewElement) {
      return false;
    }
    PlotElementViewElement elementView = (PlotElementViewElement) firstElement;
    return elementView.getPlotElement().getPlotUnit() != new PlotUnitProvider().getRootUnit();
  }

  @Override
  public void dragSetData(DragSourceEvent event) {
    TreeSelection selection = (TreeSelection) viewer.getSelection();
    event.data = selection.getFirstElement();
  }
}