package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
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
    IWorkbenchPage page = targetPart.getSite().getPage();
    for (IEditorReference reference : page.getEditorReferences()) {
      //TODO Performancekrückig?
      if (reference.getId().equals(PlotPlugin.PLOT_EDITOR_ID)) {
        closeEditorsForDeletion(page, reference);
      }
    }
    try {
      element.delete();
    }
    catch (Exception e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Could not delete plot element.", e);
    }
  }

  private void closeEditorsForDeletion(IWorkbenchPage page, IEditorReference reference) {
    closeEditor(page, reference, element);
    for (IViewElement child : element.getChildren()) {
      closeEditor(page, reference, child);      
    }
  }

  private void closeEditor(IWorkbenchPage page, IEditorReference reference, IViewElement element) {
    if (element.getDisplayName().equals(reference.getName())) {
      page.closeEditor(reference.getEditor(false), true);
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    if (structuredSelection.getFirstElement() instanceof IPlotElementViewElement) {
      element = (IPlotElementViewElement) structuredSelection.getFirstElement();
      IPlotUnit unit = element.getPlotElement().getPlotUnit();
      action.setText(NLS.bind("Delete {0} \"{1}\"", unit.getName(), element.getDisplayName()));
      action.setImageDescriptor(new DeleteIconCompositeImageDescriptor(ImageDescriptor.createFromURL(unit.getImage())));
    }
  }
}