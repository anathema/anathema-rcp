package net.sf.anathema.basics.repository.treecontent.itemtype;

import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.eclipse.core.runtime.IAdapterFactory;

public class ViewElementPageDelibleAdapterFactory implements IAdapterFactory {

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Object adaptableObject, Class adapterType) {
    IViewElement element = (IViewElement) adaptableObject;
    return element.getAdapter(adapterType);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Class[] getAdapterList() {
    return new Class[] { IPageDelible.class };
  }
}