package charactertype.solar.acceptance;

import net.sf.anathema.character.acceptance.AcceptanceCharacter;

import org.junit.After;
import org.junit.Before;

public class AbstractDefaultSolarTest {
  protected AcceptanceCharacter character;

  @Before
  public void createSolar() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId("Solar", IIntegrationConstants.DEFAULT_TEMPLATE); //$NON-NLS-1$
  }

  @After
  public void deleteSolarFolder() throws Exception {
    this.character.clear();
  }
}