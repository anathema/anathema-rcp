package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

public class NewPlotActionDelegate implements IObjectActionDelegate {
  private static final String PLOT_EDITOR_ID = "net.sf.anathema.campaign.plot.ploteditor"; //$NON-NLS-1$
  private IWorkbenchPart targetPart;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    IWorkbenchPage page = targetPart.getSite().getPage();
    IItemType itemType = PlotRepositoryUtilities.getSeriesItemType();
    IEditorInput input = new ProxyItemEditorInput(itemType.getUntitledName(), new NewPlotEditorInput(itemType));
    try {
      page.openEditor(input, PLOT_EDITOR_ID);
    }
    catch (PartInitException e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}