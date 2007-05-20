package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.campaign.plot.item.IPlotElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class PlotElementViewElement implements IViewElement {

  private final IViewElement parent;
  private final IPlotElement plotElement;
  private final IFolder folder;
  private List<IViewElement> children;

  public PlotElementViewElement(IFolder folder, IPlotElement plotElement, IViewElement parent) {
    this.folder = folder;
    this.plotElement = plotElement;
    this.parent = parent;
  }

  @Override
  public Object[] getChildren() {
    if (children == null) {
      children = new ArrayList<IViewElement>();
      for (IPlotElement element : plotElement.getChildren()) {
        children.add(new PlotElementViewElement(folder, element, this));
      }
    }
    return children.toArray(new IViewElement[children.size()]);
  }

  @Override
  public String getDisplayName() {
    IFile elementFile = folder.getFile(plotElement.getId() + ".srs"); //$NON-NLS-1$
    return new RegExPrintNameProvider().getPrintName(elementFile);
  }

  @Override
  public Image getImage() {
    return null;
  }

  @Override
  public Object getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return plotElement.getChildren().length > 0;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    // nothing to do
  }
}