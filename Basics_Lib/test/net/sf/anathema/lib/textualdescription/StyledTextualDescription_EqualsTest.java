package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StyledTextualDescription_EqualsTest {

  private StyledTextualDescription description;

  @Before
  public void createTextualDescription() throws Exception {
    description = new StyledTextualDescription();
  }

  @Test
  public void equalsDescriptionWithEqualTextParts() throws Exception {
    description.setText(new TextPart("Hasä in Grün", new TextFormat())); //$NON-NLS-1$
    StyledTextualDescription otherDescription = new StyledTextualDescription();
    otherDescription.setText(new TextPart("Hasä in Grün", new TextFormat())); //$NON-NLS-1$
    assertEquals(description, otherDescription);
  }

  @Test
  public void doesNotEqualsObject() throws Exception {
    assertFalse(description.equals(new Object()));
  }

  @Test
  public void hasSameHashCodeAsEqualDescription() throws Exception {
    assertEquals(description.hashCode(), new StyledTextualDescription().hashCode());
  }
}