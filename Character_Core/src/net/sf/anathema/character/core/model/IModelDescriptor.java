package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;

public interface IModelDescriptor {

  public boolean isSupportedFor(ICharacterTemplate template);
}