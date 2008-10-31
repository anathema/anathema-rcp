/**
 * 
 */
package net.sf.anathema.basics.repository.linkage.util;

import net.sf.anathema.basics.eclipse.runtime.IProvider;

import org.eclipse.core.runtime.IAdaptable;

public final class ResourceLinkProvider implements IProvider<ILink> {
  private final IAdaptable adaptable;

  public ResourceLinkProvider(IAdaptable adaptable) {
    this.adaptable = adaptable;
  }

  @Override
  public ILink get() {
    return new ResourceLink(adaptable);
  }
}