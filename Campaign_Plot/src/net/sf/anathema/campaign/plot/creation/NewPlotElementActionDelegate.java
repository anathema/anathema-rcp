package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

public class NewPlotElementActionDelegate implements IObjectActionDelegate {

  private IWorkbenchPart targetPart;
  private PlotElementViewElement plotElementViewElement;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    IWorkbenchPage page = targetPart.getSite().getPage();
    String untitledName = Messages.NewPlotElementActionDelegate_UntitledElementName;
    IEditorInput input = new ProxyItemEditorInput(untitledName, createNewEditorInput(untitledName));
    try {
      page.openEditor(input, PlotPlugin.PLOT_EDITOR_ID);
    }
    catch (PartInitException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.Error_PlotEditorOpenFailed, e);
    }
  }

  private NewPlotElementEditorInput createNewEditorInput(String unnamedName) {
    PlotPart plotElement = (PlotPart) plotElementViewElement.getPlotElement();
    IFolder folder = (IFolder) plotElementViewElement.getEditFile().getParent();
    IUnusedFileFactory unusedFileFactory = new UnusedFileFactory(folder, "srs"); //$NON-NLS-1$    
    ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(plotElement.getPlotUnit().getSuccessor().getImage());
    return new NewPlotElementEditorInput(unusedFileFactory, imageDescriptor, unnamedName, plotElement, folder);
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    if (structuredSelection.getFirstElement() instanceof PlotElementViewElement) {
      this.plotElementViewElement = ((PlotElementViewElement) structuredSelection.getFirstElement());
      IPlotPart part = plotElementViewElement.getPlotElement();
      action.setText(NLS.bind(Messages.NewPlotElementActionDelegate_AddNewMessage, getPlotUnitName(part)));
      action.setImageDescriptor(new NewIconCompositeImageDescriptor(ImageDescriptor.createFromURL(part.getPlotUnit()
          .getSuccessor()
          .getImage())));
    }
  }

  private String getPlotUnitName(IPlotPart part) {
    return part.getPlotUnit().getSuccessor().getName();
  }
}