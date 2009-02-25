package charactertype.mortal.acceptance;


import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public class AbstractHeroicMortalTest {
  protected AcceptanceCharacter character;

  @Before
  public void createCharacter() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Mortal", IAcceptanceConstants.HEROIC_TEMPLATE_ID); //$NON-NLS-1$
  }

  @After
  public void clearCharacater() throws Exception {
    this.character.clear();
  }
}