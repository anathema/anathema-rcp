package net.sf.anathema.basics.item.text;

import static org.junit.Assert.*;
import net.sf.anathema.lib.textualdescription.TextFormat;
import net.sf.anathema.lib.textualdescription.TextPart;

import org.junit.Before;
import org.junit.Test;

public class TitledText_FilledTest {

  private TitledText titledText;

  @Before
  public void createFilledText() {
    titledText = fillText(new TitledText());
  }

  @Test
  public void equalsFilledText() throws Exception {
    assertEquals(titledText, fillText(new TitledText()));
  }

  @Test
  public void hasSameHashCodeAsEqualObject() throws Exception {
    assertEquals(titledText.hashCode(), fillText(new TitledText()).hashCode());
  }

  private TitledText fillText(TitledText text) {
    text.getName().setText("My lucky name"); //$NON-NLS-1$
    text.getContent().setText(new TextPart("my precious", new TextFormat())); //$NON-NLS-1$
    return text;
  }
}