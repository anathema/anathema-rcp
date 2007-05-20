package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.campaign.plot.item.IPlotPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class PlotElementViewElement implements IViewElement {

  private final IViewElement parent;
  private final IPlotPart plotElement;
  private final IFolder folder;
  private List<IViewElement> children;
  private final String untitledName;

  public PlotElementViewElement(IFolder folder, IPlotPart plotElement, IViewElement parent, String untitledName) {
    this.folder = folder;
    this.plotElement = plotElement;
    this.parent = parent;
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
  public String getDisplayName() {
    IFile elementFile = folder.getFile(plotElement.getRepositoryId() + ".srs"); //$NON-NLS-1$
    String printName = new RegExPrintNameProvider().getPrintName(elementFile);
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
    return plotElement.getChildren().length > 0;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    // nothing to do
  }
}