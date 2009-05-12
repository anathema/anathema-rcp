package net.sf.anathema.character.experience.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ExperienceEntryParser_Test {

  private ExperienceEntryParser parser;

  @Before
  public void createParser() throws Exception {
    this.parser = new ExperienceEntryParser();
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsExceptionForEmptyString() throws Exception {
    parser.parse(" ");
  }

  @Test
  public void defaultsTo4Points() throws Exception {
    ExperienceEntry entry = parser.parse("Ohne Punkte");
    assertThat(entry.points, is(4));
  }

  @Test
  public void usesTextAsCommentWithoutPointsPresent() throws Exception {
    ExperienceEntry entry = parser.parse("Ohne Punkte");
    assertThat(entry.comment, is("Ohne Punkte"));
  }

  @Test
  public void recognizesPositivePointsAtTextStart() throws Exception {
    ExperienceEntry entry = parser.parse("3 Ohne Punkte");
    assertThat(entry.points, is(3));
  }

  @Test
  public void ignoresSurroundingWhiteSpace() throws Exception {
    ExperienceEntry entry = parser.parse(" 3 Ohne Punkte");
    assertThat(entry.points, is(3));
  }

  @Test
  public void recognizesNegativePointsAtTextStart() throws Exception {
    ExperienceEntry entry = parser.parse("-3 Ohne Punkte");
    assertThat(entry.points, is(-3));
  }

  @Test
  public void dropsPointsAtTextStartFromComment() throws Exception {
    ExperienceEntry entry = parser.parse("3 Mit Punkten");
    assertThat(entry.comment, is("Mit Punkten"));
  }
}