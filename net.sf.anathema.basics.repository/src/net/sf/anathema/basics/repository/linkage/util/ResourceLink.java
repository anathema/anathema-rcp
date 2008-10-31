package net.sf.anathema.basics.repository.linkage.util;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public class ResourceLink implements ILink {

  @SuppressWarnings("unused")
  private final IResource resource;

  public ResourceLink(IAdaptable adaptable) {
    this((IResource) adaptable.getAdapter(IResource.class));
  }

  public ResourceLink(IResource resource) {
    this.resource = resource;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}