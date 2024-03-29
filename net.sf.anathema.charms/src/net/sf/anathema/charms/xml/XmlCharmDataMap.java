package net.sf.anathema.charms.xml;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.IExecutableCharmDataMap;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.charms.xml.data.IDatedCharm;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlCharmDataMap implements IExecutableCharmDataMap {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private final List<Iterable<IDatedCharm>> charmCollections = new ArrayList<Iterable<IDatedCharm>>();
  private IFactory<Properties, RuntimeException> sourcePropertiesFactory;

  @Override
  public CharmDto getData(ICharmId charmId) {
    for (Iterable<IDatedCharm> charmCollection : charmCollections) {
      for (IDatedCharm charm : charmCollection) {
        if (charm.hasId(charmId)) {
          try {
            CharmDto data = charm.createDto();
            localizeSources(charmId, data);
            return data;
          }
          catch (Exception e) {
            new Logger(IPluginConstants.PLUGIN_ID).error("Error reading charm data from xml.", e);
          }
        }
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
    for (IConfigurationElement element : config.getChildren()) {
      addCharmCollection(new DatedCharmCollection(element.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
    }
    setSourceProperties(new CharmSourcePropertiesFactory(config.getContributor()));
  }

  protected void setSourceProperties(IFactory<Properties, RuntimeException> properties) {
    this.sourcePropertiesFactory = properties;

  }

  protected void addCharmCollection(Iterable<IDatedCharm> charmCollection) {
    charmCollections.add(charmCollection);
  }
}