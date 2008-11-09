package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public abstract class AbstractExtensionProvider implements IExtensionProvider{

  public AbstractExtensionProvider() {
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
}