package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class DeletePlotElementActionDelegate implements IObjectActionDelegate {

  private IWorkbenchPart targetPart;
  private IPlotElementViewElement element;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    boolean confirmed = MessageDialog.openQuestion(
        targetPart.getSite().getShell(),
        Messages.DeletePlotElementActionDelegate_Confirm_Dialog_Title,
        NLS.bind(Messages.DeletePlotElementActionDelegate_Confirm_Dialog_Message, element.getPlotElement()
            .getPlotUnit()
            .getName(), element.getDisplayName()));
    if (!confirmed) {
      return;
    }
    IWorkbenchPage page = targetPart.getSite().getPage();
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

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    if (structuredSelection.getFirstElement() instanceof IPlotElementViewElement) {
      element = (IPlotElementViewElement) structuredSelection.getFirstElement();
    }
  }
}