package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.repository.treecontent.itemtype.IPrintNameProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.campaign.plot.item.IPlotElement;
import net.sf.anathema.campaign.plot.item.ISeries;
import net.sf.anathema.campaign.plot.persistence.SeriesPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class SeriesViewElement implements IViewElement {

  private final IFolder folder;
  private final IViewElement parent;
  private final String untitledName;
  private final IPrintNameProvider printNameProvider = new RegExPrintNameProvider();
  private IItem<ISeries> item;
  private List<IViewElement> children;

  public SeriesViewElement(IFolder resource, IViewElement parent, String untitledName) {
    this.folder = resource;
    this.parent = parent;
    this.untitledName = untitledName;
  }

  private IItem<ISeries> loadItem() throws PersistenceException {
    return new SeriesPersister().load(folder);
  }
  
  private ISeries getSeries() {
    if (this.item == null) {
      try {
        this.item = loadItem();
      }
      catch (PersistenceException e) {
        // TODO Fehlerhandling
        e.printStackTrace();
      }
    }
    return item.getItemData();
  }

  @Override
  public Object[] getChildren() {
    if (children == null) {
      children = new ArrayList<IViewElement>();
      for (IPlotElement element : getSeries().getPlot().getRootElement().getChildren()) {
        children.add(new PlotElementViewElement(folder, element, this));
      }
    }
    return children.toArray(new IViewElement[children.size()]);
  }

  @Override
  public String getDisplayName() {
    String printName = printNameProvider.getPrintName(folder.getFile("main.srs")); //$NON-NLS-1$
    if (StringUtilities.isNullOrTrimEmpty(printName)) {
      return untitledName;
    }
    return printName;
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public Object getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return getStories().length > 0;
  }

  private IPlotElement[] getStories() {
    return getSeries().getPlot().getRootElement().getChildren();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    // nothing to do
  }
}