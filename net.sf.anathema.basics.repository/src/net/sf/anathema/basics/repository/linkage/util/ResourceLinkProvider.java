/**
 * 
 */
package net.sf.anathema.basics.repository.linkage.util;

import net.disy.commons.core.provider.IProvider;

import org.eclipse.core.runtime.IAdaptable;

public final class ResourceLinkProvider implements IProvider<ILink> {
  private final IAdaptable adaptable;

  public ResourceLinkProvider(IAdaptable adaptable) {
    this.adaptable = adaptable;
  }

  @Override
  public ILink getObject() {
    return new ResourceLink(adaptable);
  }
}