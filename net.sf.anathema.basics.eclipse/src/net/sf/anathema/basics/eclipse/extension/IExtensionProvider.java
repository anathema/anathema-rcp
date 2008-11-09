package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public interface IExtensionProvider {

  public IPluginExtension[] getExtensions();

  public IExtensionElement getFirst(IPredicate<IExtensionElement> predicate);
}