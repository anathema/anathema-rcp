package net.sf.anathema.campaign.plot.creation;

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
    IUnusedFileFactory unusedFileFactory = new UnusedFileFactory(folder, "srs"); //$NON-NLS-1$    
    ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(plotElement.getPlotUnit().getSuccessor().getImage());
    return new NewPlotElementEditorInput(unusedFileFactory, imageDescriptor, unnamedName, plotElement, folder);
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

// TODO Rausfinden wann sie auftritt und beseitigen
//  java.lang.ClassCastException: net.sf.anathema.character.core.repository.CharacterViewElement cannot be cast to net.sf.anathema.campaign.plot.repository.PlotElementViewElement
//  at net.sf.anathema.campaign.plot.creation.NewPlotElementHandler.getPlotElementViewElement(NewPlotElementHandler.java:75)
//  at net.sf.anathema.campaign.plot.creation.NewPlotElementHandler.updateElement(NewPlotElementHandler.java:66)
//  at org.eclipse.ui.internal.handlers.HandlerProxy.updateElement(HandlerProxy.java:364)
//  at org.eclipse.ui.internal.commands.CommandService.registerElement(CommandService.java:309)
//  at org.eclipse.ui.internal.commands.SlaveCommandService.registerElement(SlaveCommandService.java:300)
//  at org.eclipse.ui.internal.commands.SlaveCommandService.registerElementForCommand(SlaveCommandService.java:288)
//  at org.eclipse.ui.menus.CommandContributionItem.<init>(CommandContributionItem.java:217)
//  at org.eclipse.ui.internal.menus.MenuAdditionCacheEntry.createCommandAdditionContribution(MenuAdditionCacheEntry.java:316)
//  at org.eclipse.ui.internal.menus.MenuAdditionCacheEntry.createContributionItems(MenuAdditionCacheEntry.java:169)
//  at org.eclipse.ui.internal.menus.WorkbenchMenuService$3.run(WorkbenchMenuService.java:412)
//  at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:37)
//  at org.eclipse.ui.internal.menus.WorkbenchMenuService.processAdditions(WorkbenchMenuService.java:432)
//  at org.eclipse.ui.internal.menus.WorkbenchMenuService.populateContributionManager(WorkbenchMenuService.java:528)
//  at org.eclipse.ui.internal.menus.WindowMenuService.populateContributionManager(WindowMenuService.java:90)
//  at org.eclipse.ui.internal.PopupMenuExtender.addMenuContributions(PopupMenuExtender.java:360)
//  at org.eclipse.ui.internal.PopupMenuExtender.menuAboutToShow(PopupMenuExtender.java:333)
//  at org.eclipse.jface.action.MenuManager.fireAboutToShow(MenuManager.java:289)
//  at org.eclipse.jface.action.MenuManager.handleAboutToShow(MenuManager.java:394)
//  at org.eclipse.jface.action.MenuManager.access$1(MenuManager.java:390)
//  at org.eclipse.jface.action.MenuManager$2.menuShown(MenuManager.java:416)
//  at org.eclipse.swt.widgets.TypedListener.handleEvent(TypedListener.java:234)
//  at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:66)
//  at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:938)
//  at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:962)
//  at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:943)
//  at org.eclipse.swt.widgets.Control.WM_INITMENUPOPUP(Control.java:3973)
//  at org.eclipse.swt.widgets.Control.windowProc(Control.java:3690)
//  at org.eclipse.swt.widgets.Canvas.windowProc(Canvas.java:291)
//  at org.eclipse.swt.widgets.Decorations.windowProc(Decorations.java:1584)
//  at org.eclipse.swt.widgets.Shell.windowProc(Shell.java:1753)
//  at org.eclipse.swt.widgets.Display.windowProc(Display.java:4351)
//  at org.eclipse.swt.internal.win32.OS.TrackPopupMenu(Native Method)
//  at org.eclipse.swt.widgets.Menu._setVisible(Menu.java:228)

  private String getPlotUnitName(IPlotPart part) {
    return part.getPlotUnit().getSuccessor().getName();
  }
}