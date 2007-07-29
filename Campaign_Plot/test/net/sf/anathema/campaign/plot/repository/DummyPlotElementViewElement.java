package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.dnd.DummyPlotUnit;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class DummyPlotElementViewElement implements IPlotElementViewElement, IPageDelible {

  private final PlotPart plotPart;
  private final List<IPlotElementViewElement> children = new ArrayList<IPlotElementViewElement>();
  private String name;
  private IFolder parent;
  private boolean deleted;
  private boolean closed;

  public DummyPlotElementViewElement() {
    this.plotPart = new PlotPart("id", DummyPlotUnit.Plot, null); //$NON-NLS-1$
  }

  public DummyPlotElementViewElement(IPlotPart plotElement) {
    this.plotPart = new PlotPart("id", DummyPlotUnit.Plot, plotElement); //$NON-NLS-1$
  }

  @Override
  public void delete(IWorkbenchPage page) throws CoreException, IOException {
    deleted = true;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void addChild(IPlotElementViewElement child) {
    children.add(child);
  }

  @Override
  public IPlotElementViewElement[] getChildren() {
    return children.toArray(new IPlotElementViewElement[children.size()]);
  }

  @Override
  public IPlotPart getPlotElement() {
    return plotPart;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    throw new UnsupportedOperationException("Not yet implemented"); //$NON-NLS-1$
  }

  @Override
  public IViewElement getParent() {
    throw new UnsupportedOperationException("Not yet implemented"); //$NON-NLS-1$
  }

  @Override
  public boolean hasChildren() {
    return !children.isEmpty();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    throw new UnsupportedOperationException("Not yet implemented"); //$NON-NLS-1$
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    throw new UnsupportedOperationException("Not yet implemented"); //$NON-NLS-1$
  }

  @Override
  public String getDisplayName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentFolder(IFolder parent) {
    this.parent = parent;
  }

  @Override
  public boolean isPartOf(IContainer potentialParent) {
    if (parent == null) {
      return false;
    }
    return parent.equals(potentialParent);
  }

  public boolean isClosed() {
    return closed;
  }
}