package net.sf.anathema.character.core.type;

import net.sf.anathema.character.core.character.ICharacterType;

public interface ICharacterTypeCollection {

  public ICharacterType getCharacterTypeById(final String id);
}