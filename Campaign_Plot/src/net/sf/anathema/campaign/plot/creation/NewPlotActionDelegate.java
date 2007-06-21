package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

public class NewPlotActionDelegate implements IObjectActionDelegate {
  private IWorkbenchPart targetPart;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    IWorkbenchPage page = targetPart.getSite().getPage();
    IItemType itemType = PlotRepositoryUtilities.getPlotItemType();
    IEditorInput input = new ProxyItemEditorInput(itemType.getUntitledName(), new NewPlotEditorInput(itemType));
    try {
      page.openEditor(input, PlotPlugin.PLOT_EDITOR_ID);
    }
    catch (PartInitException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Failed to open Plot Editor.", e);
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}