package character.acceptance.tests;

import org.junit.After;
import org.junit.Before;

import character.acceptance.AcceptanceCharacter;

public abstract class AbstractAcceptanceTest {
  protected AcceptanceCharacter character;

  @Before
  public void createCharacter() {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId(getFolderName(), getTemplateId());
  }

  protected abstract String getTemplateId();

  protected abstract String getFolderName();

  @After
  public void clearCharacter() throws Exception {
    this.character.clear();
  }
}