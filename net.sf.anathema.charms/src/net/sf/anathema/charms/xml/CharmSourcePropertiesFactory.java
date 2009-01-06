package net.sf.anathema.charms.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import net.disy.commons.core.creation.IFactory;
import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.charms.IPluginConstants;

import org.eclipse.core.runtime.IContributor;

public class CharmSourcePropertiesFactory implements IFactory<Properties, RuntimeException> {

  private static final String PROPERTY_BASE = "data/CharmSources"; //$NON-NLS-1$
  private final IContributor contributor;

  public CharmSourcePropertiesFactory(IContributor contributor) {
    this.contributor = contributor;
  }

  @Override
  public Properties createInstance() throws RuntimeException {
    Localizer localizer = new Localizer(contributor.getName(), Locale.getDefault());
    URL propertyUrl = localizer.getPropertyUrl(PROPERTY_BASE);
    Properties properties = new Properties();
    if (propertyUrl != null) {
      loadFromUrl(properties, propertyUrl);
    }
    return properties;
  }

  private void loadFromUrl(Properties properties, URL propertyUrl) {
    InputStream inputStream = null;
    try {
      inputStream = propertyUrl.openStream();
      properties.load(inputStream);
    }
    catch (IOException e) {
      new Logger(IPluginConstants.PLUGIN_ID).error("Error loading charm source properties.", e);
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }
}