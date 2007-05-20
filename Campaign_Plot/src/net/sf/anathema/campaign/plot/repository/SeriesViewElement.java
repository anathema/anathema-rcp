package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class SeriesViewElement implements IViewElement {

  private final IFolder resource;
  private final IViewElement parent;
  private final String untitledName;

  public SeriesViewElement(IFolder resource, IViewElement parent, String untitledName) {
    this.resource = resource;
    this.parent = parent;
    this.untitledName = untitledName;
  }

  @Override
  public Object[] getChildren() {
    return new Object[0];
  }

  @Override
  public String getDisplayName() {
    return resource.getName();
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
    return false;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    // nothing to do
  }
}