package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.deletion.AbstractPageDelible;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;

public class PlotElementViewElement extends AbstractResourceViewElement implements IPlotElementViewElement {

  private final IPlotPart plotElement;
  private final IFolder folder;
  private List<PlotElementViewElement> children;

  public PlotElementViewElement(IFolder folder, IPlotPart plotElement, IViewElement parent, String untitledName) {
    super(untitledName, parent, new RegExPrintNameProvider());
    this.folder = folder;
    this.plotElement = plotElement;
  }

  @Override
  public PlotElementViewElement[] getChildren() {
    if (children == null) {
      children = new ArrayList<PlotElementViewElement>();
      for (IPlotPart element : plotElement.getChildren()) {
        String untitledName = NLS.bind(
            Messages.PlotElementViewElement_UntitledElementNameMessage,
            plotElement.getPlotUnit().getSuccessor().getName());
        children.add(new PlotElementViewElement(folder, element, this, untitledName));
      }
    }
    return children.toArray(new PlotElementViewElement[children.size()]);
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return ImageDescriptor.createFromURL(plotElement.getPlotUnit().getImage());
  }

  @Override
  public IFile getEditFile() {
    return folder.getFile(plotElement.getRepositoryId() + ".srs"); //$NON-NLS-1$
  }

  public IPlotPart getPlotElement() {
    return plotElement;
  }

  @Override
  public boolean hasChildren() {
    return plotElement.getChildren().length > 0;
  }

  @Override
  public String toString() {
    return plotElement.getRepositoryId();
  }

  public void saveHierarchy(IProgressMonitor monitor) throws IOException, CoreException {
    new PlotPersister().saveHierarchy(folder, plotElement, monitor);
  }

  @Override
  public boolean isPartOf(IContainer parent) {
    return folder.equals(parent);
  }

  @Override
  protected IPageDelible createDelible() {
    return new AbstractPageDelible(new PlotElementCloseHandler(this)) {
      @Override
      protected void delete() throws CoreException, IOException {
        // TODO Monitor
        NullProgressMonitor monitor = new NullProgressMonitor();
        if (plotElement.getParent() == null) {
          getEditFile().getParent().delete(true, monitor);
        }
        else {
          deleteFromHierarchy(monitor);
        }
      }
    };
  }

  private void deleteFromHierarchy(IProgressMonitor monitor) throws CoreException, IOException {
    for (PlotElementViewElement element : getChildren()) {
      element.deleteFromHierarchy(monitor);
    }
    plotElement.getParent().removeChild(plotElement);
    /*
     * Die Hierarchie muss jedes mal gespeichert werden, um das Element auch aus dem Baumansicht zu entfernen. Geschieht
     * das nicht, taucht das Element für einen Augenblick mit seinem Dateinamen auf, bevor es gelöscht wird.
     */
    saveHierarchy(monitor);
    getEditFile().delete(true, false, monitor);
  }
}