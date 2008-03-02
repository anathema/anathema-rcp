package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextPartTest {

  private TextPart textPart;

  @Before
  public void createTextPart() {
    textPart = createEqualTextPart();
  }

  @Test
  public void equalsTextPartWithText() throws Exception {
    assertEquals(textPart, createEqualTextPart());
  }

  @Test
  public void hasSameHashCodeAsEqualTextPart() throws Exception {
    assertEquals(textPart.hashCode(), createEqualTextPart().hashCode());
  }

  private TextPart createEqualTextPart() {
    return new TextPart("Hasä im Feld", new TextFormat()); //$NON-NLS-1$
  }
}