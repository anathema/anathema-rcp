package net.sf.anathema.basics.repository.persistence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.persistence.IAnathemaXmlConstants;
import net.sf.anathema.framework.persistence.IItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class RepositoryItemPersister implements IAnathemaXmlConstants, IItemPersister {

  public void save(Element element, IItem< ? > item) {
    String repositoryId = item.getRepositoryLocation().getId();
    Ensure.ensureNotNull("Repository item must have an id for saving.", repositoryId); //$NON-NLS-1$
    element.addAttribute(ATTRIB_REPOSITORY_ID, repositoryId);
    element.addAttribute(ATTRIB_REPOSITORY_PRINT_NAME, item.getPrintName());
  }

  public void load(Element element, IItem< ? > item) throws PersistenceException {
    item.getRepositoryLocation().setId(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_ID));
    item.setPrintName(ElementUtilities.getRequiredAttrib(element, ATTRIB_REPOSITORY_PRINT_NAME));
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do;
  }
}