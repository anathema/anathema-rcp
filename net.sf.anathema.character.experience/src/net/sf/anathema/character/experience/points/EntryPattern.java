package net.sf.anathema.character.experience.points;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryPattern {

  private final String patternString;
  private final int pointGroup;
  private final int commentGroup;
  private CharSequence trimmedText;
  private Matcher matcher;

  public static EntryPattern CreatePointsFront(String pattern) {
    return new EntryPattern(pattern, 1, 2);
  }

  public static EntryPattern CreatePointsBack(String pattern) {
    return new EntryPattern(pattern, 2, 1);
  }

  private EntryPattern(String pattern, int pointGroup, int commentGroup) {
    this.patternString = pattern;
    this.pointGroup = pointGroup;
    this.commentGroup = commentGroup;
  }

  public int getPoints() {
    String pointString = matcher.group(pointGroup);
    return Integer.valueOf(pointString);
  }

  public String getComment() {
    return matcher.group(commentGroup).trim();
  }

  public boolean matches(String text) {
    Pattern pattern = Pattern.compile(patternString);
    matcher = pattern.matcher(text);
    return matcher.matches();
  }
}