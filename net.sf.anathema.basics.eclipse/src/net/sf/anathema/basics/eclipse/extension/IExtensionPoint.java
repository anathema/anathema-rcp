package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public interface IExtensionPoint {

  public IPluginExtension[] getExtensions();

  public IExtensionElement getFirst(IPredicate<IExtensionElement> predicate);
}