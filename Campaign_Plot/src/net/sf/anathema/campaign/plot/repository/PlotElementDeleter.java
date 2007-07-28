package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPage;

public class PlotElementDeleter {

  private final IWorkbenchPage page;
  private final IPlotElementViewElement element;

  public PlotElementDeleter(IWorkbenchPage page, IPlotElementViewElement element) {
    this.page = page;
    this.element = element;
  }

  public void delete() {
    try {
      element.delete(page);
    }
    catch (Exception e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.DeletePlotElementActionDelegate_Deletion_Error, e);
    }
  }
}
