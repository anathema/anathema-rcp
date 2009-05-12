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
  public void allowsCommentToStartWithNumber() throws Exception {
    ExperienceEntry entry = parser.parse("13Wilde Männer 3");
    assertThat(entry.comment, is("13Wilde Männer"));
    assertThat(entry.points, is(3));
  }

  @Test
  public void usesStandaloneStartingMinusForComment() throws Exception {
    ExperienceEntry entry = parser.parse("- Minus");
    assertThat(entry.comment, is("- Minus"));
  }

  @Test
  public void findsPointsAtBackOfString() throws Exception {
    ExperienceEntry entry = parser.parse("In the Back 2");
    assertThat(entry.points, is(2));
  }

  @Test
  public void findsNegativePointsAtBackOfString() throws Exception {
    ExperienceEntry entry = parser.parse("In the Back -2");
    assertThat(entry.points, is(-2));
  }

  @Test
  public void dropsWhitespaceAfterCommentWithPointsAtBack() throws Exception {
    ExperienceEntry entry = parser.parse("In the Back 2");
    assertThat(entry.comment, is("In the Back"));
  }
}