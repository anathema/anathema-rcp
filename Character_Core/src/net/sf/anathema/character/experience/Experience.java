package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public final class Experience extends AbstractModel implements IExperience {
  @Override
  public boolean isExperienced() {
    return false;
  }
}