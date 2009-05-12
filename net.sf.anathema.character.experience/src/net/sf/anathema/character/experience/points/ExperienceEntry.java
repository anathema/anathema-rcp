package net.sf.anathema.character.experience.points;

public class ExperienceEntry {

  public int points;
  public String comment;

  public static ExperienceEntry CreateForPointsAndComment(int points, String comment) {
    ExperienceEntry entry = new ExperienceEntry();
    entry.points = points;
    entry.comment = comment;
    return entry;
  }
}