package charactertype.mortal.acceptance;

import net.sf.anathema.character.acceptance.AcceptanceCharacter;

import org.junit.After;
import org.junit.Before;

public class AbstractHeroicMortalTest {
  protected AcceptanceCharacter character;

  @Before
  public void createCharacter() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Mortal", IMortalAcceptanceConstants.HEROIC_TEMPLATE_ID); //$NON-NLS-1$
  }

  @After
  public void clearCharacater() throws Exception {
    this.character.clear();
  }
}