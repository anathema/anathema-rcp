package net.sf.anathema.charms.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.charms.xml.data.IDatedCharm;
import net.sf.anathema.charms.xml.data.IDatedCharmCollection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;

public class XmlCharmDataMap implements ICharmDataMap {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private IDatedCharmCollection charmCollection;
  private Properties sourceProperties;

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
    for (SourceDto sourceDto : data.sources) {
      localizeSource(id, sourceDto);
    }
  }

  private void localizeSource(ICharmId id, SourceDto sourceDto) {
    String source = sourceDto.source;
    sourceDto.addition = sourceProperties.getProperty(MessageFormat.format("{0}.{1}.Page", source, id.getId())); //$NON-NLS-1$
    sourceDto.source = sourceProperties.getProperty(source, source);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    setCharmCollection(new DatedCharmCollection(config.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
    setSourceProperties(new Properties());
    // TODO: nicht schon beim Programmstart laden
    loadSourceProperties(config.getContributor());
  }

  private void loadSourceProperties(IContributor contributor) {
    Localizer localizer = new Localizer(contributor.getName(), Locale.getDefault());
    URL propertyUrl = localizer.getPropertyUrl("data/CharmSources"); //$NON-NLS-1$
    if (propertyUrl == null) {
      return;
    }
    loadSoureProperties(propertyUrl);
  }

  private void loadSoureProperties(URL propertyUrl) {
    InputStream inputStream = null;
    try {
      inputStream = propertyUrl.openStream();
      sourceProperties.load(inputStream);
    }
    catch (IOException e) {
      new Logger(IPluginConstants.PLUGIN_ID).error("Error loading charm source properties.", e);
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }

  protected void setSourceProperties(Properties properties) {
    this.sourceProperties = properties;

  }

  protected void setCharmCollection(IDatedCharmCollection charmCollection) {
    this.charmCollection = charmCollection;
  }
}