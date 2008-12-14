package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.IClosure;

public interface IExtensionPoint {

  public IPluginExtension[] getExtensions();

  public IExtensionElement getFirst(IPredicate<IExtensionElement> predicate);

  public void forAllDo(IClosure<IExtensionElement> closure);
}