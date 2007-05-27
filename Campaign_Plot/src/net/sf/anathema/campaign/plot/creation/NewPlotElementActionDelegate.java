package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class NewPlotElementActionDelegate implements IObjectActionDelegate {

  private IWorkbenchPart targetPart;
  private ISelection lastSelection;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    IWorkbenchPage page = targetPart.getSite().getPage();
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor("Hasä.not"); //$NON-NLS-1$
    String unnamedName = "Unnamed Plot Element"; //$NON-NLS-1$
    IEditorInput input = new ProxyItemEditorInput(unnamedName, createNewEditorInput(unnamedName));
    try {
      page.openEditor(input, defaultEditor.getId());
    }
    catch (PartInitException e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }

  private NewPlotElementEditorInput createNewEditorInput(String unnamedName) {
    TreeSelection treeSelection = (TreeSelection) lastSelection;
    PlotElementViewElement plotViewElement = (PlotElementViewElement) treeSelection.getFirstElement();
    PlotPart plotElement = (PlotPart) plotViewElement.getPlotElement();
    IFolder folder = (IFolder) plotViewElement.getEditFile().getParent();
    IUnusedFileFactory unusedFileFactory = new UnusedFileFactory(folder, "srs"); //$NON-NLS-1$
    String resourcePath = "icons/Folder" + plotElement.getPlotUnit().getSuccessor().getPersistenceString() + "16.png";  //$NON-NLS-1$ //$NON-NLS-2$
    ImageDescriptor imageDescriptor = PlotPlugin.getImageDescriptor(resourcePath);
    return new NewPlotElementEditorInput(unusedFileFactory, imageDescriptor, unnamedName, plotElement, folder);
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    this.lastSelection = selection;
  }
}