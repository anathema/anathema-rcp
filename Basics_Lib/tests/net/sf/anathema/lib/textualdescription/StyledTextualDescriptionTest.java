package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.text.font.FontStyle;

import org.junit.Before;
import org.junit.Test;

public class StyledTextualDescriptionTest {

  private StyledTextualDescription description;

  private boolean evaluateIsBold(int start, int length) {
    return description.containsFormat(start, length, new IPredicate<ITextFormat>() {
      @Override
      public boolean evaluate(ITextFormat format) {
        return format.getFontStyle().isBold();
      }
    });
  }

  @Before
  public void createDescription() {
    description = new StyledTextualDescription();
  }

  @Test
  public void emptyDescriptionContainsNoFormat() throws Exception {
    assertFalse(description.containsFormat(0, 0, new IPredicate<ITextFormat>() {
      @Override
      public boolean evaluate(ITextFormat arg0) {
        return true;
      }
    }));
  }

  @Test
  public void doFindExistingSingleFormat() throws Exception {
    description.setText(new ITextPart[] { new TextPart("Hasä", new TextFormat(FontStyle.BOLD, true)) }); //$NON-NLS-1$
    assertTrue(evaluateIsBold(0, 1));
  }

  @Test
  public void doNotFindNonExistingFormat() throws Exception {
    description.setText(new ITextPart[] { new TextPart("Hasä", new TextFormat(FontStyle.ITALIC, true)) }); //$NON-NLS-1$
    assertFalse(evaluateIsBold(0, 1));
  }

  @Test
  public void findFormatInSucceedingParts() throws Exception {
    TextPart notMatchingPart = new TextPart("Hasän", new TextFormat(FontStyle.ITALIC, true)); //$NON-NLS-1$
    TextPart matchingPart = new TextPart("tum", new TextFormat(FontStyle.BOLD, false)); //$NON-NLS-1$
    description.setText(new ITextPart[] { notMatchingPart, matchingPart });
    assertTrue(evaluateIsBold(0, 6));
  }

  @Test
  public void equalFormatsDontInterfereWithRange() throws Exception {
    description.setText(new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.ITALIC, false)), //$NON-NLS-1$
        new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("toller ", new TextFormat(FontStyle.BOLD, false)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.ITALIC, false))); //$NON-NLS-1$
    assertTrue("Must evaluate to bold.", evaluateIsBold(11, 10)); //$NON-NLS-1$
  }

  @Test
  public void everythingIsSetToggleFromPlainToBold() throws Exception {
    ITextPart[] parts = { new TextPart("ein", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
    };
    description.setText(parts);
    description.toggleFontStyle(0, 3, FontStyle.BOLD);
    assertTrue(evaluateIsBold(0, 3));
  }

  @Test
  public void everythingIsToggledFromBoldToPlain() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.BOLD, true))); //$NON-NLS-1$
    description.toggleFontStyle(0, 3, FontStyle.BOLD);
    assertFalse(evaluateIsBold(0, 3));
  }

  @Test
  public void onlyLeadingPartialTextPartBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleFontStyle(0, 2, FontStyle.BOLD);
    assertTrue(evaluateIsBold(0, 2));
    assertFalse(evaluateIsBold(2, 1));
  }

  @Test
  public void onlyTailingPartialTextPartBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true))); //$NON-NLS-1$
    description.toggleFontStyle(2, 1, FontStyle.BOLD);
    assertFalse(evaluateIsBold(0, 2));
    assertTrue(evaluateIsBold(2, 1));
  }

  @Test
  public void tailingAndNextTextPartBolded() throws Exception {
    description.setText(
        new TextPart("ein", new TextFormat(FontStyle.PLAIN, true)), new TextPart("Hasä", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$ //$NON-NLS-2$
    description.toggleFontStyle(2, 3, FontStyle.BOLD);
    assertFalse(evaluateIsBold(0, 2));
    assertTrue(evaluateIsBold(2, 3));
    assertFalse(evaluateIsBold(5, 1));
  }

  @Test
  public void multipleSuccessiveTextPartsBolded() throws Exception {
    description.setText(new TextPart("ein", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("toller", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("Hasä", new TextFormat(FontStyle.PLAIN, false))); //$NON-NLS-1$
    description.toggleFontStyle(1, 11, FontStyle.BOLD);
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
    description.toggleFontStyle(4, 6, FontStyle.BOLD);
    ITextPart[] textParts = description.getTextParts();
    assertStyleAndLength(textParts[0], FontStyle.PLAIN, 4);
    assertStyleAndLength(textParts[1], FontStyle.BOLD, 6);
    assertStyleAndLength(textParts[2], FontStyle.PLAIN, 5);

  }

  private void assertStyleAndLength(ITextPart textPart, FontStyle style, int length) {
    assertEquals(style, textPart.getFormat().getFontStyle());
    assertEquals(length, textPart.getLength());
  }
}