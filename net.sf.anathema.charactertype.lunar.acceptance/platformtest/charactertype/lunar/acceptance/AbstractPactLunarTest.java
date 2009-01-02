package charactertype.lunar.acceptance;

import net.sf.anathema.character.acceptance.AcceptanceCharacter;

import org.junit.After;
import org.junit.Before;

public class AbstractPactLunarTest {
  protected AcceptanceCharacter character;

  @Before
  public void createSolar() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Pactlunar", ILunarAcceptanceConstants.PACT_LUNAR_ID); //$NON-NLS-1$
  }

  @After
  public void deleteSolarFolder() throws Exception {
    this.character.clear();
  }
}