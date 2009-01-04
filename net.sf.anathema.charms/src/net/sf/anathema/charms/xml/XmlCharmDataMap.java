package net.sf.anathema.charms.xml;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.charms.xml.data.IDatedCharm;
import net.sf.anathema.charms.xml.data.IDatedCharmCollection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlCharmDataMap implements ICharmDataMap {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private IDatedCharmCollection charmCollection;

  @Override
  public CharmDto getData(ICharmId charmId) {
    for (IDatedCharm charm : charmCollection) {
      if (charm.hasId(charmId)) {
        return charm.createDto();
      }
    }
    return null;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    setCharmCollection(new DatedCharmCollection(config.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
  }

  protected void setCharmCollection(IDatedCharmCollection charmCollection) {
    this.charmCollection = charmCollection;
  }
}