package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.dnd.DummyPlotUnit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class DummyPlotElementViewElement implements IPlotElementViewElement {

  private final PlotPart plotPart = new PlotPart("id", DummyPlotUnit.Plot, null); //$NON-NLS-1$
  private final List<IPlotElementViewElement> children = new ArrayList<IPlotElementViewElement>();
  private final String name;

  public DummyPlotElementViewElement(String name) {
    this.name = name;
  }

  @Override
  public void delete() throws CoreException, IOException {
    throw new UnsupportedOperationException("Not yet implemented"); //$NON-NLS-1$
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
}