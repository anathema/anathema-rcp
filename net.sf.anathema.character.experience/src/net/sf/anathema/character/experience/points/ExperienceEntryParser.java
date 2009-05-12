package net.sf.anathema.character.experience.points;

public class ExperienceEntryParser {

  private final static String FRONT_POINT_PATTERN = "(-?\\d*)\\s*(.*)";

  public ExperienceEntry parse(String text) {
    String trimmedText = text.trim();
    if (trimmedText.isEmpty()) {
      throw new IllegalArgumentException("Empty String not allowed.");
    }
    int points = 4;
    String[] tokens = trimmedText.split(" "); //$NON-NLS-1$
    String comment = trimmedText;
    if (isInteger(tokens[0])) {
      points = Integer.valueOf(tokens[0]);
      comment = trimmedText.substring(tokens[0].length() + 1);
    }
    return ExperienceEntry.CreateForPointsAndComment(points, comment);
  }

  private boolean isInteger(String token) {
    try {
      Integer.parseInt(token);
    }
    catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
}