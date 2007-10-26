package net.sf.anathema.campaign.plot.creation;

import java.net.URL;
import java.util.Map;

import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class NewPlotElementHandler extends AbstractHandler implements IElementUpdater {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    PlotElementViewElement plotElementViewElement = getPlotElementViewElement(selection);
    String untitledName = NLS.bind(
        Messages.NewPlotElementActionDelegate_UntitledElementNameMessage,
        getPlotUnitName(plotElementViewElement.getPlotElement()));
    IEditorInput input = new ProxyItemEditorInput(untitledName, createNewEditorInput(
        untitledName,
        plotElementViewElement));
    try {
      HandlerUtil.getActiveSiteChecked(event).getPage().openEditor(input, PlotPlugin.PLOT_EDITOR_ID);
      return null;
    }
    catch (PartInitException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.Error_PlotEditorOpenFailed, e);
      throw new ExecutionException(Messages.Error_PlotEditorOpenFailed, e);
    }
  }

  private NewPlotElementEditorInput createNewEditorInput(
      String unnamedName,
      PlotElementViewElement plotElementViewElement) {
    PlotPart plotElement = (PlotPart) plotElementViewElement.getPlotElement();
    IFolder folder = (IFolder) plotElementViewElement.getEditFile().getParent();
    String extension = PlotRepositoryUtilities.getPlotItemType().getFileExtension();
    IUnusedFileFactory unusedFileFactory = new UnusedFileFactory(folder, extension);
    URL imageUrl = plotElement.getPlotUnit().getSuccessor().getImage();
    return new NewPlotElementEditorInput(unusedFileFactory, imageUrl, unnamedName, plotElement, folder);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateElement(UIElement element, Map parameters) {
    IWorkbenchWindow window = (IWorkbenchWindow) parameters.get("org.eclipse.ui.IWorkbenchWindow"); //$NON-NLS-1$
    ISelection selection = window.getSelectionService().getSelection();
    IPlotPart part = getPlotElementViewElement(selection).getPlotElement();
    element.setText(NLS.bind(Messages.NewPlotElementActionDelegate_AddNewMessage, getPlotUnitName(part)));
    element.setIcon(new NewIconCompositeImageDescriptor(ImageDescriptor.createFromURL(part.getPlotUnit()
        .getSuccessor()
        .getImage())));
  }

  private PlotElementViewElement getPlotElementViewElement(ISelection selection) {
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    PlotElementViewElement plotElementViewElement = ((PlotElementViewElement) structuredSelection.getFirstElement());
    return plotElementViewElement;
  }

  private String getPlotUnitName(IPlotPart part) {
    return part.getPlotUnit().getSuccessor().getName();
  }
}