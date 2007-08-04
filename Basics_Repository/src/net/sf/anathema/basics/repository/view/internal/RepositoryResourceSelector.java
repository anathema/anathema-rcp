package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.linkage.IResourceSelector;
import net.sf.anathema.basics.repository.treecontent.IViewElementProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StructuredSelection;

public class RepositoryResourceSelector implements IResourceSelector {

  private final IViewElementProvider contentProvider;
  private final ISelector selector;

  public RepositoryResourceSelector(IViewElementProvider contentProvider, ISelector selector) {
    this.contentProvider = contentProvider;
    this.selector = selector;
  }
  
  @Override
  public void setSelection(IResource resource) {
    IViewElement viewElement = contentProvider.getViewElement(resource);
    selector.setSelection(viewElement == null ? null : new StructuredSelection(viewElement));
  }
}