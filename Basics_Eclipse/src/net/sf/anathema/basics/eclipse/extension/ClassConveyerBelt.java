package net.sf.anathema.basics.eclipse.extension;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.AcceptAllPredicate;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.logging.Logger;

import org.eclipse.core.runtime.IExecutableExtension;

public class ClassConveyerBelt<T extends IExecutableExtension> {

  private final String pluginId;
  private final IPluginExtension[] extensions;
  private final Class<T> objectClass;

  public ClassConveyerBelt(EclipseExtensionPoint extensionPoint, Class<T> objectClass) {
    this(extensionPoint.getPluginId(), objectClass, extensionPoint.getExtensions());
  }

  public ClassConveyerBelt(String pluginId, Class<T> objectClass, IPluginExtension... extensions) {
    this.pluginId = pluginId;
    this.extensions = extensions;
    this.objectClass = objectClass;
  }

  public List<T> getAllObjects() {
    return getObjects(new AcceptAllPredicate<IExtensionElement>());
  }

  public List<T> getObjects(IPredicate<IExtensionElement> predicate) {
    List<T> allObjects = new ArrayList<T>();
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          if (predicate.evaluate(element)) {
            allObjects.add(element.getAttributeAsObject("class", objectClass)); //$NON-NLS-1$
          }
        }
        catch (Exception e) {
          Logger logger = new Logger(pluginId);
          logger.error("Could not instantiate object for extension point", e);
        }
      }
    }
    return allObjects;
  }

  public T getFirstObject(IPredicate<IExtensionElement> predicate) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          if (predicate.evaluate(element)) {
            return element.getAttributeAsObject("class", objectClass); //$NON-NLS-1$
          }
        }
        catch (Exception e) {
          Logger logger = new Logger(pluginId);
          logger.error("Could not instantiate object for extension point", e);
        }
      }
    }
    return null;
  }
}