package charactertype.solar.acceptance;


import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public class AbstractDefaultSolarTest {
  protected AcceptanceCharacter character;

  @Before
  public void createSolar() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId("Solar", IAcceptanceConstants.DEFAULT_TEMPLATE); //$NON-NLS-1$
  }

  @After
  public void deleteSolarFolder() throws Exception {
    this.character.clear();
  }
}