package net.sf.anathema.character.experience.points;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExperienceEntryParser {

  private final static String FRONT_POINT_PATTERN = "(-?\\d*)\\s*(.*)";

  public ExperienceEntry parse(String text) {
    String trimmedText = text.trim();
    if (trimmedText.isEmpty()) {
      throw new IllegalArgumentException("Empty String not allowed.");
    }
    Pattern pattern = Pattern.compile(FRONT_POINT_PATTERN);
    Matcher matcher = pattern.matcher(trimmedText);
    matcher.matches();
    String pointString = matcher.group(1);
    String commentString = matcher.group(2);
    int points = pointString.isEmpty() ? 4 : Integer.valueOf(pointString);
    return ExperienceEntry.CreateForPointsAndComment(points, commentString);
  }
}