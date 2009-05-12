package net.sf.anathema.character.experience.points;

import java.util.regex.Matcher;

public class EntryPattern {

  private final String pattern;
  private final int pointGroup;
  private final int commentGroup;

  public static EntryPattern CreatePointsFront(String pattern) {
    return new EntryPattern(pattern, 1, 2);
  }

  public static EntryPattern CreatePointsBack(String pattern) {
    return new EntryPattern(pattern, 2, 1);
  }

  private EntryPattern(String pattern, int pointGroup, int commentGroup) {
    this.pattern = pattern;
    this.pointGroup = pointGroup;
    this.commentGroup = commentGroup;
  }

  public String getPattern() {
    return pattern;
  }

  public int getPoints(Matcher matcher) {
    String pointString = matcher.group(pointGroup);
    return Integer.valueOf(pointString);
  }

  public String getComment(Matcher matcher) {
    return matcher.group(commentGroup);
  }
}