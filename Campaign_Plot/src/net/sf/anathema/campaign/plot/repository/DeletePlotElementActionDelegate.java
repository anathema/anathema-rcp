package net.sf.anathema.campaign.plot.repository;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IObjectActionDelegate;
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
    new PlotElementDeleter(targetPart.getSite().getPage(), element).delete();
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    if (structuredSelection.getFirstElement() instanceof IPlotElementViewElement) {
      element = (IPlotElementViewElement) structuredSelection.getFirstElement();
    }
  }
}