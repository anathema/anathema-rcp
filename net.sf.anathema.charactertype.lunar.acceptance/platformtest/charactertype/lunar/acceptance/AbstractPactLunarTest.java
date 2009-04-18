package charactertype.lunar.acceptance;


import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public class AbstractPactLunarTest {
  protected AcceptanceCharacter character;

  @Before
  public void createLunar() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Pactlunar", IAcceptanceConstants.PACT_LUNAR_ID); //$NON-NLS-1$
  }

  @After
  public void deleteLunarFolder() throws Exception {
    this.character.clear();
  }
}