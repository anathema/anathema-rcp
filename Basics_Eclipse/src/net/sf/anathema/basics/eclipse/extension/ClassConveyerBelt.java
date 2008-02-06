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
  private final IPredicate<IExtensionElement> predicate;

  public ClassConveyerBelt(
      EclipseExtensionPoint extensionPoint,
      Class<T> objectClass) {
    this(extensionPoint.getPluginId(), objectClass, new AcceptAllPredicate<IExtensionElement>());
  }

  public ClassConveyerBelt(
      EclipseExtensionPoint extensionPoint,
      Class<T> objectClass,
      IPredicate<IExtensionElement> predicate) {
    this(extensionPoint.getPluginId(), objectClass, predicate, extensionPoint.getExtensions());
  }

  public ClassConveyerBelt(String pluginId, Class<T> objectClass, IPluginExtension... extensions) {
    this(pluginId, objectClass, new AcceptAllPredicate<IExtensionElement>(), extensions);
  }

  public ClassConveyerBelt(
      String pluginId,
      Class<T> objectClass,
      IPredicate<IExtensionElement> predicate,
      IPluginExtension... extensions) {
    this.pluginId = pluginId;
    this.predicate = predicate;
    this.extensions = extensions;
    this.objectClass = objectClass;
  }

  public List<T> getAllObjects() {
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

  public T getFirstObject() {
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