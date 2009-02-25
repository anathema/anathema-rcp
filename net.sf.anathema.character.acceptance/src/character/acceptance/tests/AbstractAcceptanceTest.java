package character.acceptance.tests;

import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

@SuppressWarnings("nls")
public abstract class AbstractAcceptanceTest {
  protected AcceptanceCharacter character;

  @Before
  public void createCharacter() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId("TestCharacter", getTemplateId());
  }

  protected abstract String getTemplateId();

  @After
  public void clearCharacter() throws Exception {
    this.character.clear();
  }
}