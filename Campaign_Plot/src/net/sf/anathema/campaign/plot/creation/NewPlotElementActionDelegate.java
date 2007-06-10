package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.repository.EnumInternationalizer;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

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
    String unnamedName = "Unnamed Plot Element"; //$NON-NLS-1$
    IEditorInput input = new ProxyItemEditorInput(unnamedName, createNewEditorInput(unnamedName));
    try {
      page.openEditor(input, PlotPlugin.PLOT_EDITOR_ID);
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
    ImageDescriptor imageDescriptor = getSuccessorImage(plotElement);
    return new NewPlotElementEditorInput(unusedFileFactory, imageDescriptor, unnamedName, plotElement, folder);
  }

  private ImageDescriptor getSuccessorImage(IPlotPart plotElement) {
    String resourcePath = "icons/Folder" + plotElement.getPlotUnit().getSuccessor().getPersistenceString() + "16.png";//$NON-NLS-1$ //$NON-NLS-2$
    return PlotPlugin.getDefaultInstance().getImageDescriptor(resourcePath);
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    this.lastSelection = selection;
    StructuredSelection structuredSelection = (StructuredSelection) lastSelection;
    if (structuredSelection.getFirstElement() instanceof PlotElementViewElement) {
      IPlotPart part = ((PlotElementViewElement) structuredSelection.getFirstElement()).getPlotElement();
      action.setText(NLS.bind(Messages.NewPlotElementActionDelegate_AddNewMessage, getPlotUnitName(part)));
      action.setImageDescriptor(new NewIconCompositeImageDescriptor(getSuccessorImage(part)));
    }
  }

  private String getPlotUnitName(IPlotPart part) {
    return new EnumInternationalizer(net.sf.anathema.campaign.plot.repository.Messages.class).getDisplayName(part.getPlotUnit()
        .getSuccessor());
  }
}