package net.sf.anathema.character.experience.points;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ExperienceEntry {

  public int points;
  public String comment;

  public static ExperienceEntry CreateForPointsAndComment(int points, String comment) {
    ExperienceEntry entry = new ExperienceEntry();
    entry.points = points;
    entry.comment = comment;
    return entry;
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