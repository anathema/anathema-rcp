package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

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
    try {
      for (IEditorReference reference : page.getEditorReferences()) {
        if (reference.getId().equals(PlotPlugin.PLOT_EDITOR_ID)) {
          closeEditorsForDeletion(page, reference);
        }
      }
      element.delete();
    }
    catch (Exception e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.DeletePlotElementActionDelegate_Deletion_Error, e);
    }
  }

  private void closeEditorsForDeletion(IWorkbenchPage page, IEditorReference reference) throws PartInitException {
    if (element.getDisplayName().equals(reference.getName())) {
      page.closeEditor(reference.getEditor(false), false);
      return;
    }
    IPlotChild newChild = (IPlotChild) reference.getEditorInput().getAdapter(IPlotChild.class);
    if (newChild != null) {
      if (element.getPlotElement().equals(newChild.getParent())) {
        page.closeEditor(reference.getEditor(false), false);
        return;
      }
    }
    closeChildren(page, reference, element.getChildren());
  }

  private void closeChildren(IWorkbenchPage page, IEditorReference reference, IPlotElementViewElement[] children) {
    for (IPlotElementViewElement child : children) {
      if (child.getDisplayName().equals(reference.getName())) {
        page.closeEditor(reference.getEditor(false), false);
        return;
      }
      closeChildren(page, reference, child.getChildren());
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    if (structuredSelection.getFirstElement() instanceof IPlotElementViewElement) {
      element = (IPlotElementViewElement) structuredSelection.getFirstElement();
      IPlotUnit unit = element.getPlotElement().getPlotUnit();
      action.setText(NLS.bind(Messages.DeletePlotElementActionDelegate_DeleteActionText_Message, unit.getName()));
      action.setImageDescriptor(new DeleteIconCompositeImageDescriptor(ImageDescriptor.createFromURL(unit.getImage())));
    }
  }
}