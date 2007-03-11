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
  public void equalsFormatsDontMix() throws Exception {
    ITextPart[] parts = {
        new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.ITALIC, false)), //$NON-NLS-1$
        new TextPart("ein ", new TextFormat(FontStyle.PLAIN, true)), //$NON-NLS-1$
        new TextPart("toller ", new TextFormat(FontStyle.BOLD, false)), //$NON-NLS-1$
        new TextPart("Hasä ", new TextFormat(FontStyle.ITALIC, false))}; //$NON-NLS-1$
    description.setText(parts);
    assertTrue("Must evaluate to bold.", evaluateIsBold(11, 10)); //$NON-NLS-1$
  }
}