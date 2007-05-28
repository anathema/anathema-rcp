package net.sf.anathema.character.core.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.IItemTypeViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class CharacterViewElementFactory implements IItemTypeViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    return new ArrayList<IViewElement>();
  }

  @Override
  public void setItemType(IItemType itemType) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // TODO Auto-generated method stub

  }
}