package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;

public class NullModelDescriptor implements IModelDescriptor {

  @Override
  public boolean isSupportedFor(ICharacterTemplate template) {
    return false;
  }
}