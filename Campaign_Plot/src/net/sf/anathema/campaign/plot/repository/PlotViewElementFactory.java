package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractFolderBasedViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;

public class PlotViewElementFactory extends AbstractFolderBasedViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      IPlotPart rootPart;
      try {
        rootPart = new PlotPersister().load(folder);
        elements.add(new PlotElementViewElement(folder, rootPart, parent, getItemType().getUntitledName()));
      }
      catch (PersistenceException e) {
        PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.PlotViewElementFactory_LoadingPlotErrorMessage, e);
      }
    }
    return elements;
  }
}