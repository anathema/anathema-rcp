package net.sf.anathema.charms.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CharmPrerequisite {

  private final String source;
  private final String destination;

  public CharmPrerequisite(String source, String destination) {
    this.source = source;
    this.destination = destination;
  }

  public String getSource() {
    return source;
  }

  public String getDestination() {
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
}