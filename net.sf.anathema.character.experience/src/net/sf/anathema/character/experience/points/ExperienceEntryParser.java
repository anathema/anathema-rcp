package net.sf.anathema.character.experience.points;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExperienceEntryParser {

  private final List<EntryPattern> patterns = new ArrayList<EntryPattern>() {
    {
      add(EntryPattern.CreatePointsFront("(-?\\d+)\\s+(.*)")); //$NON-NLS-1$
      add(EntryPattern.CreatePointsBack("(.*)\\s+(-?\\d+)")); //$NON-NLS-1$
    }
  };

  public ExperienceEntry parse(String text) {
    String trimmedText = text.trim();
    if (trimmedText.isEmpty()) {
      throw new IllegalArgumentException("Empty String not allowed.");
    }
    for (EntryPattern entryPattern : patterns) {
      Pattern pattern = Pattern.compile(entryPattern.getPattern());
      Matcher matcher = pattern.matcher(trimmedText);
      if (matcher.matches()) {
        String commentString = entryPattern.getComment(matcher);
        int points = entryPattern.getPoints(matcher);
        return ExperienceEntry.CreateForPointsAndComment(points, commentString.trim());
      }
    }
    return ExperienceEntry.CreateForPointsAndComment(4, trimmedText);
  }
}