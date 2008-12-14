package net.sf.anathema.basics.eclipse.extension.internal;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.eclipse.core.runtime.IConfigurationElement;

public final class ExtensionElementTransformer implements
    ITransformer<IConfigurationElement, IExtensionElement> {
  @Override
  public IExtensionElement transform(IConfigurationElement input) {
    return new ExtensionElement(input);
  }
}