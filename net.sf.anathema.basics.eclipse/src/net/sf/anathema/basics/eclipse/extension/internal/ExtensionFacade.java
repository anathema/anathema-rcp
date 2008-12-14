package net.sf.anathema.basics.eclipse.extension.internal;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

public class ExtensionFacade implements IPluginExtension {

  private final IExtension eclipseExtension;

  public ExtensionFacade(IExtension eclipseExtension) {
    this.eclipseExtension = eclipseExtension;
  }

  @Override
  public IExtensionElement[] getElements() {
    IConfigurationElement[] elements = eclipseExtension.getConfigurationElements();
    return transform(elements);
  }

  private IExtensionElement[] transform(IConfigurationElement[] elements) {
    return ArrayUtilities.transform(elements, IExtensionElement.class, new ExtensionElementTransformer());
  }

  @Override
  public String getContributorId() {
    return eclipseExtension.getContributor().getName();
  }
}