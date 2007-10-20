package net.sf.anathema.character.core.character.internal;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.junit.Test;

public class CharacterIdTest {

  @Test
  public void adaptsToContainer() throws Exception {
    IContainer container = EasyMock.createMock(IContainer.class);
    CharacterId characterId = new CharacterId(container);
    assertSame(container, characterId.getAdapter(IContainer.class));
  }
}