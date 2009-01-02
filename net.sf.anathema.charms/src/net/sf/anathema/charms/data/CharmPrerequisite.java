package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.ICharmId;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CharmPrerequisite {

  private final ICharmId source;
  private final ICharmId destination;

  public CharmPrerequisite(ICharmId source, ICharmId destination) {
    this.source = source;
    this.destination = destination;
  }

  public ICharmId getSource() {
    return source;
  }

  public ICharmId getDestination() {
    return destination;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(obj, this);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return source + "->" + destination; //$NON-NLS-1$
  }

  public boolean connects(String charmId) {
    boolean isDestination = charmId.equals(getDestination().getId());
    boolean isSource = getSource() == null ? false : charmId.equals(getSource().getId());
    return isDestination || isSource;
  }
}