package net.sf.anathema.charms.xml;

import java.text.MessageFormat;
import java.util.Properties;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.charms.xml.data.IDatedCharm;
import net.sf.anathema.charms.xml.data.IDatedCharmCollection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlCharmDataMap implements ICharmDataMap {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private IDatedCharmCollection charmCollection;
  private IFactory<Properties, RuntimeException> sourcePropertiesFactory;

  @Override
  public CharmDto getData(ICharmId charmId) {
    for (IDatedCharm charm : charmCollection) {
      if (charm.hasId(charmId)) {
        CharmDto data = charm.createDto();
        localizeSources(charmId, data);
        return data;
      }
    }
    return null;
  }

  private void localizeSources(ICharmId id, CharmDto data) {
    Properties sourceProperties = sourcePropertiesFactory.createInstance();
    for (SourceDto sourceDto : data.sources) {
      localizeSource(id, sourceDto, sourceProperties);
    }
  }

  private void localizeSource(ICharmId id, SourceDto sourceDto, Properties properties) {
    String source = sourceDto.source;
    sourceDto.addition = properties.getProperty(MessageFormat.format("{0}.{1}.Page", source, id.getId())); //$NON-NLS-1$
    sourceDto.source = properties.getProperty(source, source);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    setCharmCollection(new DatedCharmCollection(config.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
    setSourceProperties(new CharmSourcePropertiesFactory(config.getContributor()));
  }

  protected void setSourceProperties(IFactory<Properties, RuntimeException> properties) {
    this.sourcePropertiesFactory = properties;

  }

  protected void setCharmCollection(IDatedCharmCollection charmCollection) {
    this.charmCollection = charmCollection;
  }
}