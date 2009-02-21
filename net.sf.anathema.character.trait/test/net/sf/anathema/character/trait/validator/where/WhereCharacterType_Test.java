package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.validator.ValidationDtoObjectMother;

import org.junit.Before;
import org.junit.Test;

public class WhereCharacterType_Test {

  private static final String TEST_TYPE = "test.charactertype.id"; //$NON-NLS-1$
  private IWhere where;

  @Before
  public void createWhere() throws Exception {
    this.where = new WhereCharacterType(TEST_TYPE);
  }

  @Test
  public void evaluatesEqualModelIdTrue() throws Exception {
    assertTrue(where.evaluate(ValidationDtoObjectMother.ForCharacterType(TEST_TYPE)));
  }

  @Test
  public void evaluatesOtherModelIdFalse() throws Exception {
    assertFalse(where.evaluate(ValidationDtoObjectMother.ForCharacterType("other.test.charactertype.id"))); //$NON-NLS-1$
  }
}