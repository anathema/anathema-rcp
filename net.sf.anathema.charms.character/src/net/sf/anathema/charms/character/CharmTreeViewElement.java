package net.sf.anathema.charms.character;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IConfigurableViewElement;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharmTreeViewElement implements IViewElement {

  private final IConfigurableViewElement parent;
  private final String treeId;

  public CharmTreeViewElement(IConfigurableViewElement parent, String treeId) {
    this.parent = parent;
    this.treeId = treeId;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public IViewElement getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public URL getImageUrl() {
    return parent.getImageUrl();
  }

  @Override
  public Object getAdapter(Class adapter) {
    return parent.getAdapter(adapter);
  }

  @Override
  public String getDisplayName() {
    return treeId;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    parent.openEditor(page);
  }
}