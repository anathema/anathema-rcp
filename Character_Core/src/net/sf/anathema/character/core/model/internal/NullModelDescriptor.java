package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.IModelDescriptor;

public class NullModelDescriptor implements IModelDescriptor {

  @Override
  public boolean isSupportedBy(ICharacterTemplate template) {
    return false;
  }
}