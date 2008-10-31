package net.sf.anathema.basics.eclipse.extension.internal;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
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
    return ArrayUtilities.transform(
        eclipseExtension.getConfigurationElements(),
        IExtensionElement.class,
        new ITransformer<IConfigurationElement, IExtensionElement>() {
          @Override
          public IExtensionElement transform(IConfigurationElement input) {
            return new ExtensionElement(input);
          }
        });
  }

  @Override
  public String getContributorId() {
    return eclipseExtension.getContributor().getName();
  }
}