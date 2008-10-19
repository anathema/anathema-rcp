package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.linkage.ILinkageSelector;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.treecontent.IViewElementProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.jface.viewers.StructuredSelection;

public class RepositoryResourceSelector implements ILinkageSelector {

  private final IViewElementProvider contentProvider;
  private final ISelector selector;

  public RepositoryResourceSelector(IViewElementProvider contentProvider, ISelector selector) {
    this.contentProvider = contentProvider;
    this.selector = selector;
  }
  
  @Override
  public void setSelection(ILink linkageId) {
    IViewElement viewElement = contentProvider.getViewElement(linkageId);
    selector.setSelection(viewElement == null ? null : new StructuredSelection(viewElement));
  }
}