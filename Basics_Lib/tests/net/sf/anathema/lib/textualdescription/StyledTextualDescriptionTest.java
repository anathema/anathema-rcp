package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;
import net.disy.commons.core.text.font.FontStyle;

import org.junit.Before;
import org.junit.Test;

public class StyledTextualDescriptionTest {

  private StyledTextualDescription description;

  private boolean isDominantlyBold(int start, int length) {
    return description.isDominant(TextAspect.Bold, start, length);
  }

  @Before
  public void createDescription() {
    description = new StyledTextualDescription();
  }

  @Test
  public void emptyDescriptionContainsNoFormat() throws Exception {
    assertFalse(description.isDominant(TextAspect.Italic, 0, 0));
  }

  @Test
  public void doFindExistingSingleFormat() throws Exception {
    description.setText(new ITextPart[] { new TextPart("Hasä", new TextFormat(FontStyle.BOLD, true)) }); //$NON-NLS-1$
    assertTrue(isDominantlyBold(0, 1));
  }

  @Test
  public void doNotFindNonExistingFormat() throws Exception {
    description.setText(new ITextPart[] { new TextPart("Hasä", new TextFormat(FontStyle.ITALIC, true)) }); //$NON-NLS-1$
    assertFalse(isDominantlyBold(0, 1));
  }

  @Test
  public void findFormatInSucceedingParts() throws Exception {
    TextPart notMatchingPart = new TextPart("Hasän", new TextFormat(FontStyle.ITALIC, true)); //$NON-NLS-1$
    TextPart matchingPart = new TextPart("tum", new TextFormat(FontStyle.BOLD, false)); //$NON-NLS-1$
    description.setText(new ITextPart[] { notMatchingPart, matchingPart });
    assertFalse(isDominantlyBold(0, 6));
  }

  @Test
  public void equalFormatsDontInterfereWithRange() throws Exception {
    description.setText(new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.BOLD, false)), //$NON-NLS-1$
        new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("toller ", new TextFormat(FontStyle.ITALIC, false)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.BOLD, false))); //$NON-NLS-1$
    assertFalse("Must not be bold.", isDominantlyBold(11, 10)); //$NON-NLS-1$
  }

  @Test
  public void everythingIsToggledFromPlainToBold() throws Exception {
    ITextPart[] parts = { new TextPart("ein", new TextFormat(FontStyle.PLAIN, true))}; //$NON-NLS-1$
    description.setText(parts);
    description.toggleAspect(TextAspect.Bold, 0, 3);
    assertTrue(isDominantlyBold(0, 3));
  }

  @Test
  public void everythingIsToggledFromBoldToPlain() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.BOLD, true))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 0, 3);
    assertFalse(isDominantlyBold(0, 3));
  }

  @Test
  public void onlyLeadingPartialTextPartBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 0, 2);
    assertTrue(isDominantlyBold(0, 2));
    assertFalse(isDominantlyBold(2, 1));
  }

  @Test
  public void onlyTailingPartialTextPartBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 2, 1);
    assertFalse(isDominantlyBold(0, 2));
    assertTrue(isDominantlyBold(2, 1));
  }

  @Test
  public void tailingAndNextTextPartBolded() throws Exception {
    description.setText(
        new TextPart("ein", new TextFormat(FontStyle.PLAIN, true)), new TextPart("Hasä", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$ //$NON-NLS-2$
    description.toggleAspect(TextAspect.Bold, 2, 3);
    assertFalse(isDominantlyBold(0, 2));
    assertTrue(isDominantlyBold(2, 3));
    assertFalse(isDominantlyBold(5, 1));
  }

  @Test
  public void multipleSuccessiveTextPartsBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("toller", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("Hasä", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 1, 11);
    ITextPart[] textParts = description.getTextParts();
    assertStyleAndLength(textParts[0], FontStyle.PLAIN, 1);
    assertStyleAndLength(textParts[1], FontStyle.BOLD, 2);
    assertStyleAndLength(textParts[2], FontStyle.BOLD, 6);
    assertStyleAndLength(textParts[3], FontStyle.BOLD, 3);
    assertStyleAndLength(textParts[4], FontStyle.PLAIN, 1);
  }

  @Test
  public void middleOfTextPartIsBolded() throws Exception {
    description.setText(new TextPart("Ein toller Hasä", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 4, 6);
    ITextPart[] textParts = description.getTextParts();
    assertStyleAndLength(textParts[0], FontStyle.PLAIN, 4);
    assertStyleAndLength(textParts[1], FontStyle.BOLD, 6);
    assertStyleAndLength(textParts[2], FontStyle.PLAIN, 5);
  }

  @Test
  public void middleOfTextPartIsUnbolded() throws Exception {
    description.setText(new TextPart("Ein toller Hasä", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 4, 6);
    description.toggleAspect(TextAspect.Bold, 4, 6);
    ITextPart[] textParts = description.getTextParts();
    assertStyleAndLength(textParts[0], FontStyle.PLAIN, 4);
    assertStyleAndLength(textParts[1], FontStyle.PLAIN, 6);
    assertStyleAndLength(textParts[2], FontStyle.PLAIN, 5);
  }

  private void assertStyleAndLength(ITextPart textPart, FontStyle style, int length) {
    assertEquals(style, textPart.getFormat().getFontStyle());
    assertEquals(length, textPart.getLength());
  }

  @Test
  public void plainestPartDictatesBehaviour() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, false)), //$NON-NLS-1$
        new TextPart("toller", new TextFormat(FontStyle.BOLD, false))); //$NON-NLS-1$
    description.toggleAspect(TextAspect.Bold, 0, 5);
    ITextPart[] textParts = description.getTextParts();
    assertStyleAndLength(textParts[0], FontStyle.BOLD, 3);
    assertStyleAndLength(textParts[1], FontStyle.BOLD, 2);
    assertStyleAndLength(textParts[2], FontStyle.BOLD, 4);
  }
  
  @Test
  public void isDirtyAfterToggleAspect() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$
    description.setDirty(false);
    description.toggleAspect(TextAspect.Bold, 0, 2);
    assertTrue(description.isDirty());
  }
  
  @Test
  public void isDirtyAfterReplaceText() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$
    description.setDirty(false);
    description.replaceText(0, 2, "Tum"); //$NON-NLS-1$
    assertTrue(description.isDirty());
  }
}