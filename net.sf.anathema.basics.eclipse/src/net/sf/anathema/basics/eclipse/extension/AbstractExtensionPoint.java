package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.IClosure;

public abstract class AbstractExtensionPoint implements IExtensionPoint {

  public AbstractExtensionPoint() {
    super();
  }

  @Override
  public IExtensionElement getFirst(IPredicate<IExtensionElement> predicate) {
    for (IPluginExtension extension : getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        if (predicate.evaluate(element)) {
          return element;
        }
      }
    }
    return null;
  }

  @Override
  public void forAllDo(IClosure<IExtensionElement> closure) {
    for (IPluginExtension extension : getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        closure.execute(element);
      }
    }
  }
}