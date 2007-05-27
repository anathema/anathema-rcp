package net.sf.anathema.campaign.plot;

import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

public class NewPlotElementActionDelegate implements IObjectActionDelegate {

  private IWorkbenchPart targetPart;
  private ISelection selection;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    TreeSelection treeSelection = (TreeSelection) selection;
    PlotElementViewElement plotViewElement = (PlotElementViewElement) treeSelection.getFirstElement();
    PlotPart plotElement = (PlotPart) plotViewElement.getPlotElement();
//    String repositoryId;
//    plotElement.addChild(repositoryId);
//    IWorkbenchPartSite site = targetPart.getSite();
//    IWorkbenchPage page = site.getPage();
//    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor("Hasä.srs");
//    IEditorInput input = new ProxyItemEditorInput(NotesRepositoryUtilities.getNotesItemType());
//    try {
//      page.openEditor(input, defaultEditor.getId());
//    }
//    catch (PartInitException e) {
//      // TODO Fehlerhandling
//      e.printStackTrace();
//    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    this.selection = selection;
  }
 }