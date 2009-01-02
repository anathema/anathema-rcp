package net.sf.anathema.charms.tree;

import java.text.MessageFormat;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class CharmId implements ICharmId {

  private final String idPattern;
  private final String primaryTrait;

  public CharmId(String idPattern, String primaryTrait) {
    this.idPattern = idPattern;
    this.primaryTrait = primaryTrait;
  }

  public String getIdPattern() {
    return idPattern;
  }
  
  public String getId() {
    return MessageFormat.format(idPattern, primaryTrait);
  }
  
  public String getPrimaryTrait() {
    return primaryTrait;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharmId)){
      return false;
    }
    return getId().equals(((CharmId)obj).getId());
  }
  
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
  
  @Override
  public String toString() {
    return getId();
  }
}