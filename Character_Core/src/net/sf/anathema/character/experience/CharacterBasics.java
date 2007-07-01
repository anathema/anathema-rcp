package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public final class CharacterBasics extends AbstractModel implements ICharacterBasics {
  @Override
  public boolean isExperienced() {
    return false;
  }
}