package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IViewSite;

public final class PlotDropListener extends ViewerDropAdapter {
  private PlotPartMove partMove;
  private RelativeLocation location;
  private PlotElementViewElement targetElement;
  private final IViewSite site;

  public PlotDropListener(Viewer viewer, IViewSite site) {
    super(viewer);
    this.site = site;
  }

  @Override
  public boolean performDrop(Object data) {
    //TODO Zeige Progress Monitor an. 
    IProgressMonitor monitor = site.getActionBars().getStatusLineManager().getProgressMonitor();
    monitor.beginTask("Moving", IProgressMonitor.UNKNOWN);
    try {
      partMove.moveTo(location);
      targetElement.saveHierarchy(monitor);
    }
    catch (Exception e) {
      // TODO Rückspulen des Moves
      e.printStackTrace();
      return false;
    }
    finally {
      monitor.done();
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