package charactertype.db.acceptance;


import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public class AbstractDynastDbTest {
  protected AcceptanceCharacter character;

  @Before
  public void createCharacter() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(
        "DynastDb", "net.sf.anathema.character.template.dynastdb"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @After
  public void clearCharacter() throws Exception {
    this.character.clear();
  }
}