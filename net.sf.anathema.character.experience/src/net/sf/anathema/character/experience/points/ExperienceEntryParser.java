package net.sf.anathema.character.experience.points;

import java.util.ArrayList;
import java.util.List;

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
      if (entryPattern.matches(trimmedText)) {
        return createEntryFrom(entryPattern);
      }
    }
    return ExperienceEntry.CreateForPointsAndComment(4, trimmedText);
  }

  private ExperienceEntry createEntryFrom(EntryPattern pattern) {
    String comment = pattern.getComment();
    int points = pattern.getPoints();
    return ExperienceEntry.CreateForPointsAndComment(points, comment);
  }
}