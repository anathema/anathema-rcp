package net.sf.anathema.basics.eclipse.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.core.runtime.IExecutableExtension;

public class ClassConveyerBelt<T extends IExecutableExtension> {

  private final String pluginId;
  private final IPluginExtension[] extensions;
  private final Class<T> objectClass;

  public ClassConveyerBelt(EclipseExtensionPoint extensionPoint, Class<T> objectClass) {
    this(extensionPoint.getPluginId(), extensionPoint.getExtensions(), objectClass);
  }

  public ClassConveyerBelt(String pluginId, IPluginExtension[] extensions, Class<T> objectClass) {
    this.pluginId = pluginId;
    this.extensions = extensions;
    this.objectClass = objectClass;
  }

  public List<T> getAllObjects() {
    List<T> allObjects = new ArrayList<T>();
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          allObjects.add(element.getAttributeAsObject("class", objectClass)); //$NON-NLS-1$
        }
        catch (Exception e) {
          Logger logger = new Logger(pluginId);
          logger.error("Could not instantiate object for extension point", e);
        }
      }
    }
    return allObjects;
  }
}