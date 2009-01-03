package net.sf.anathema.character.core.conversion;

import java.io.InputStream;
import java.util.Properties;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class CharacterTypeConverter {
  private static final String resourcePath = "resources/charactertypeconversion.properties"; //$NON-NLS-1$

  public String getNewType(String oldType) {
    Properties properties = new Properties();
    InputStream inputStream = null;
    try {
      inputStream = ResourceUtils.getInputStream(CharacterCorePlugin.ID, resourcePath);
      properties.load(inputStream);
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
    return properties.getProperty(oldType);
  }
}