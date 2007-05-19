package net.sf.anathema.basics.repository.persistence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.framework.item.persistence.IAnathemaXmlConstants;
import net.sf.anathema.framework.item.persistence.IItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class RepositoryItemPersister implements IAnathemaXmlConstants, IItemPersister {

  public <D extends IItemData> void save(Element element, IItem<D> item) {
    if (!isRepositoryItem(item)) {
      return;
    }
    String repositoryId = getRepositoryLocation(item).getId();
    Ensure.ensureNotNull("Repository item must have an id for saving.", repositoryId); //$NON-NLS-1$
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getPrintName());
  }

  private <D extends IItemData> IItemRepositoryLocation getRepositoryLocation(IItem<D> item) {
    RepositoryLocationItem< ? > role = item.getRole(RepositoryLocationItem.class);
    return role.getRepositoryLocation();
  }

  private boolean isRepositoryItem(IItem< ? > item) {
    return item.hasRole(RepositoryLocationItem.class);
  }

  public <D extends IItemData> void load(Element element, IItem<D> item) throws PersistenceException {
    String repositoryId = element.attributeValue(ATTRIB_REPOSITORY_ID);
    if (repositoryId == null) {
      return;
    }
    item.addRole(new RepositoryLocationItem<D>());
    getRepositoryLocation(item).setId(repositoryId);
    item.setPrintName(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_PRINT_NAME));

  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do;
  }
}