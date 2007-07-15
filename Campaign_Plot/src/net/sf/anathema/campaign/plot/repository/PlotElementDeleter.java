package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;

public class PlotElementDeleter {

  private final IWorkbenchPage page;
  private final IPlotElementViewElement element;

  public PlotElementDeleter(IWorkbenchPage page, IPlotElementViewElement element) {
    this.page = page;
    this.element = element;
  }

  public void delete() {
    PlotElementCloseHandler closeHandler = new PlotElementCloseHandler(new PageEditorCloser(page), element);
    try {
      for (IEditorReference reference : page.getEditorReferences()) {
        if (reference.getId().equals(PlotPlugin.PLOT_EDITOR_ID)) {
          closeHandler.closeIfRequired(reference);
        }
      }
      element.delete();
    }
    catch (Exception e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.DeletePlotElementActionDelegate_Deletion_Error, e);
    }
  }
}
