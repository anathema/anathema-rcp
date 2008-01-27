package net.sf.anathema.character.core.model.internal;

import static org.junit.Assert.*;

import net.sf.anathema.character.core.character.CharacterId;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFolder;
import org.junit.Before;
import org.junit.Test;

public class CharacterIdTest {

  private CharacterId characterId;
  private IFolder folder;

  @Before
  public void createCharacterId() throws Exception {
    folder = EasyMock.createMock(IFolder.class);
    characterId = new CharacterId(folder);
  }

  @Test
  public void notEqualsObject() throws Exception {
    assertFalse(characterId.equals(new Object()));
  }

  @Test
  public void equalsIdWithSameFolder() throws Exception {
    CharacterId other = new CharacterId(folder);
    assertTrue(characterId.equals(other));
    assertEquals(other.hashCode(), characterId.hashCode());
  }
  
  @Test
  public void notEqualsCharacterIdWithOtherFolder() throws Exception {
    assertFalse(characterId.equals(new CharacterId(null)));
  }
}