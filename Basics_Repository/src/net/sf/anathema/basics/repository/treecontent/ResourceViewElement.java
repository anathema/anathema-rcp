package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.IPrintNameProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;

public class ResourceViewElement extends AbstractResourceViewElement {
  private final IFile file;

  public ResourceViewElement(
      IFile resource,
      IPrintNameProvider printNameProvider,
      IViewElement parent,
      String untitledName) {
    super(untitledName, parent, printNameProvider);
    this.file = resource;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  protected IFile getEditFile() {
    return file;
  }
}