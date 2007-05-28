package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

public final class PlotDropListener extends ViewerDropAdapter {
  private PlotPartMove partMove;
  private RelativeLocation location;
  private PlotElementViewElement targetElement;

  public PlotDropListener(Viewer viewer) {
    super(viewer);
  }

  @Override
  public boolean performDrop(Object data) {
    try {
      partMove.moveTo(location);
      //TODO: Echter Progressmonitor
      targetElement.saveHierarchy(new NullProgressMonitor());
    }
    catch (Exception e) {
      // TODO Rückspulen des Moves
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    int currentLocation = getCurrentLocation();
    if (!(target instanceof PlotElementViewElement) || currentLocation == 0) {
      return false;
    }
    StructuredSelection selection = (StructuredSelection) getViewer().getSelection();
    PlotElementViewElement sourceElement = (PlotElementViewElement) selection.getFirstElement();
    targetElement = (PlotElementViewElement) target;
    IPlotPart sourcePart = sourceElement.getPlotElement();
    IPlotPart targetPart = targetElement.getPlotElement();
    this.location = RelativeLocation.getByOrdinal(currentLocation - 1);
    this.partMove = new PlotPartMove(sourcePart, targetPart);
    return partMove.isValid();
  }
}