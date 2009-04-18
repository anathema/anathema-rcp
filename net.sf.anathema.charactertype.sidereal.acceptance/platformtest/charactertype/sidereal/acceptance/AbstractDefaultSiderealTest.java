package charactertype.sidereal.acceptance;


import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public class AbstractDefaultSiderealTest {
  protected AcceptanceCharacter character;

  @Before
  public void createSidereal() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Sidereal", IAcceptanceConstants.DEFAULT_TEMPLATE); //$NON-NLS-1$
  }

  @After
  public void deleteSiderealFolder() throws Exception {
    this.character.clear();
  }
}