package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class NewPlotWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    openEditorForNewPlot(getWorkbenchPage());
    return true;
  }

  public static void openEditorForNewPlot(IWorkbenchPage page) {
    IItemType itemType = PlotRepositoryUtilities.getPlotItemType();
    IEditorInput input = new ProxyItemEditorInput(itemType.getUntitledName(), new NewPlotEditorInput());
    try {
      page.openEditor(input, PlotPlugin.PLOT_EDITOR_ID);
    }
    catch (PartInitException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.Error_PlotEditorOpenFailed, e);
    }
  }
}