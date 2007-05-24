package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class PlotElementViewElement extends AbstractResourceViewElement {

  private final IPlotPart plotElement;
  private final IFolder folder;
  private List<IViewElement> children;
  private final String untitledName;

  public PlotElementViewElement(IFolder folder, IPlotPart plotElement, IViewElement parent, String untitledName) {
    super(untitledName, parent, new RegExPrintNameProvider());
    this.folder = folder;
    this.plotElement = plotElement;
    this.untitledName = untitledName;
  }

  @Override
  public Object[] getChildren() {
    if (children == null) {
      children = new ArrayList<IViewElement>();
      for (IPlotPart element : plotElement.getChildren()) {
        children.add(new PlotElementViewElement(folder, element, this, untitledName));
      }
    }
    return children.toArray(new IViewElement[children.size()]);
  }

  @Override
  protected IFile getEditFile() {
    return folder.getFile(plotElement.getRepositoryId() + ".srs"); //$NON-NLS-1$
  }

  @Override
  public boolean hasChildren() {
    return plotElement.getChildren().length > 0;
  }
}