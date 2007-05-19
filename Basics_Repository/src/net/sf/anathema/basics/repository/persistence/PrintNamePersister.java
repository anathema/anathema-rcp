package net.sf.anathema.basics.repository.persistence;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.framework.item.persistence.IItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class PrintNamePersister implements IItemPersister {

  private static final String ATTRIB_REPOSITORY_PRINT_NAME = "repositoryPrintName"; //$NON-NLS-1$

  public <D extends IItemData> void save(Element element, IItem<D> item) {
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getPrintName());
  }

  public <D extends IItemData> void load(Element element, IItem<D> item) throws PersistenceException {
    // nothing to do
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do;
  }
}