package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

public final class PlotDropListener extends ViewerDropAdapter {
  public PlotDropListener(Viewer viewer) {
    super(viewer);
  }

  @Override
  public boolean performDrop(Object data) {
    return false;
  }

  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    if (!(target instanceof PlotElementViewElement)) {
      return false;
    }
    StructuredSelection selection = (StructuredSelection) getViewer().getSelection();
    PlotElementViewElement sourceElement = (PlotElementViewElement) selection.getFirstElement();
    PlotElementViewElement targetElement = (PlotElementViewElement) target;
    IPlotPart sourcePart = sourceElement.getPlotElement();
    IPlotPart targetPart = targetElement.getPlotElement();
    RelativeLocation location = RelativeLocation.getByOrdinal(getCurrentLocation() - 1);
    return new PlotPartMove(sourcePart, targetPart).isValid(location);
  }
}