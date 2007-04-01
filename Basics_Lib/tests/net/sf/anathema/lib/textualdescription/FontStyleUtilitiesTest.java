package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;
import net.disy.commons.core.text.font.FontStyle;

import org.junit.Test;

public class FontStyleUtilitiesTest {

  @Test
  public void plainDoesNotChangeAnything() throws Exception {
    assertEquals(FontStyle.PLAIN, FontStyleUtilities.combineFontStyles(FontStyle.PLAIN, FontStyle.PLAIN));
    assertEquals(FontStyle.BOLD, FontStyleUtilities.combineFontStyles(FontStyle.PLAIN, FontStyle.BOLD));
    assertEquals(FontStyle.BOLD, FontStyleUtilities.combineFontStyles(FontStyle.BOLD, FontStyle.PLAIN));
    assertEquals(FontStyle.BOLD_ITALIC, FontStyleUtilities.combineFontStyles(FontStyle.PLAIN, FontStyle.BOLD_ITALIC));
    assertEquals(FontStyle.BOLD_ITALIC, FontStyleUtilities.combineFontStyles(FontStyle.BOLD_ITALIC, FontStyle.PLAIN));
    assertEquals(FontStyle.ITALIC, FontStyleUtilities.combineFontStyles(FontStyle.PLAIN, FontStyle.ITALIC));
    assertEquals(FontStyle.ITALIC, FontStyleUtilities.combineFontStyles(FontStyle.ITALIC, FontStyle.PLAIN));
  }
  
  @Test
  public void boldEliminatesBolds() throws Exception {
    assertEquals(FontStyle.PLAIN, FontStyleUtilities.combineFontStyles(FontStyle.BOLD, FontStyle.BOLD));
    assertEquals(FontStyle.ITALIC, FontStyleUtilities.combineFontStyles(FontStyle.BOLD_ITALIC, FontStyle.BOLD));
  }

  @Test
  public void italicEliminatesItalics() throws Exception {
    assertEquals(FontStyle.PLAIN, FontStyleUtilities.combineFontStyles(FontStyle.ITALIC, FontStyle.ITALIC));
    assertEquals(FontStyle.BOLD, FontStyleUtilities.combineFontStyles(FontStyle.BOLD_ITALIC, FontStyle.ITALIC));
  }
}