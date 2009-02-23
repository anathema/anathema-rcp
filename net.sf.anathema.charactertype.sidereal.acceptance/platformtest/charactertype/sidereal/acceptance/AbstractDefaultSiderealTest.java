package charactertype.sidereal.acceptance;

import net.sf.anathema.character.acceptance.AcceptanceCharacter;

import org.junit.After;
import org.junit.Before;

public class AbstractDefaultSiderealTest {
  protected AcceptanceCharacter character;

  @Before
  public void createSidereal() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "Sidereal", ISiderealAcceptanceConstants.DEFAULT_TEMPLATE_ID); //$NON-NLS-1$
  }

  @After
  public void deleteSiderealFolder() throws Exception {
    this.character.clear();
  }
}