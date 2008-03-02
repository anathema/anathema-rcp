package net.sf.anathema.basics.item.text;

import static org.junit.Assert.*;
import net.sf.anathema.lib.textualdescription.TextFormat;
import net.sf.anathema.lib.textualdescription.TextPart;

import org.junit.Before;
import org.junit.Test;
public class TitledText_EmptyTest {

  private TitledText titledText;

  @Before
  public void createEmptyText() {
    titledText = new TitledText();
  }

  @Test
  public void equalsEmptyText() throws Exception {
    assertEquals(titledText, new TitledText());
  }

  @Test
  public void doesNotEqualTextWithName() throws Exception {
    TitledText namedText = new TitledText();
    namedText.getName().setText("name"); //$NON-NLS-1$
    assertFalse(titledText.equals(namedText));
  }

  @Test
  public void doesNotEqualTextWithContent() throws Exception {
    TitledText namedText = new TitledText();
    namedText.getContent().setText(new TextPart("content", new TextFormat())); //$NON-NLS-1$
    assertFalse(titledText.equals(namedText));
  }

  @Test
  public void doesNotEqualObject() throws Exception {
    assertFalse(titledText.equals(new Object()));
  }

  @Test
  public void hasSameHashCodeAsEqualObject() throws Exception {
    assertEquals(titledText.hashCode(), new TitledText().hashCode());
  }
}